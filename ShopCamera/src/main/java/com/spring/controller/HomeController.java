package com.spring.controller;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.unbescape.html.HtmlEscape;

import com.spring.cart.Utils;
import com.spring.domain.Cart;
import com.spring.domain.Category;
import com.spring.domain.Order;
import com.spring.domain.Product;
import com.spring.domain.State;
import com.spring.domain.Users;
import com.spring.repo.impl.CategoryRepoImpl;
import com.spring.repo.impl.OrderRepoImpl;
import com.spring.repo.impl.ProductRepoImpl;
import com.spring.repo.impl.UserRepoImpl;
import com.spring.service.CategoryService;
import com.spring.service.ProcessingService;
import com.spring.service.ProductService;
import com.spring.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("/CameraGiamSat")
public class HomeController {

	@Autowired
	private ProcessingService processService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private ProductRepoImpl productRepoImpl;
	@Autowired
	private CategoryRepoImpl categoryRepoImpl;
	@Autowired
	private OrderRepoImpl orderRepoImpl;

	@Autowired
	private UserRepoImpl userRepoImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	Cart c;
	@Autowired
	private JavaMailSender sender;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/CameraGiamSat/index";
	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		List<Category> listCate = this.categoryService.findAll();
		String message = "Hello Spring Boot + JSP";
		model.addAttribute("slides", categoryService.getCateBySlide());
		model.addAttribute("listCate", listCate);
		model.addAttribute("message", message);
		model.addAttribute("listCity", productService.getCity());
		model.addAttribute("productService", productService);
		// gio hang
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "index";
	}

	// @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	// public String listProcess(Model model) {
	// // model.addAttribute("listCustomer", processService.findAll().size());
	// List<Processing> p = processService.findAll();
	//
	// model.addAttribute("p", p);
	// return "index";
	// }

	@RequestMapping("/productdetail/{id}")
	public String productDetail(@PathVariable(value = "id") int id, Model model, HttpServletRequest request) {
		List<Category> listCate = this.categoryService.findAll();
		Product p = productService.getProduct(id);
		model.addAttribute("product", p);
		model.addAttribute("listCate", listCate);
		model.addAttribute("detaiProduct", HtmlEscape.unescapeHtml(p.getDetail_product()));
		model.addAttribute("digital", HtmlEscape.unescapeHtml(p.getDigital()));
		// gio hang
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "product_detail";
	}
	//
	// @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	// public String home(Model model) {
	//
	// List<Processing> p = processService.findAll();
	// model.addAttribute("p", p);
	// // model.addAttribute("listCate", listCate);
	// // model.addAttribute("cate", cate);
	// // model.addAttribute("slide", slide);
	// model.addAttribute("categoryService", categoryService);
	// model.addAttribute("slides", this.categoryService.getCateBySlide());
	// model.addAttribute("productService", productService);
	//
	// return "home";
	// }

	@RequestMapping("/category/{id_cate}")
	public String category(Model model, @PathVariable(value = "id_cate") int idCate,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpServletRequest request) {
		List<Category> listCate = this.categoryService.findAll();
		model.addAttribute("listCate", listCate);
		List<Product> list = productRepoImpl.getProductByCate(idCate, page, 12);
		model.addAttribute("list", list);
		model.addAttribute("page", page);

		model.addAttribute("pageNumber", productRepoImpl.pageNumber(idCate));
		model.addAttribute("cate", categoryRepoImpl.getCategory(idCate));

		// gio hang
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "category";
	}

	@RequestMapping(value = "/thanhtoan", method = RequestMethod.GET)
	public String thanhToan(HttpSession session, HttpServletRequest request, Model model) {
		// gio hang

		if (session.getAttribute("user") == null) {
			return "redirect:/CameraGiamSat/dangky";
		}
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "thanhtoan";
	}

	@RequestMapping(value = "/dangky", method = RequestMethod.GET)
	public String dangky(Model model, HttpServletRequest request) {
		model.addAttribute("users", new Users());
		// gio hang
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "kiemtrathanhtoan";
	}

	// @RequestMapping(value = "/dangky2", method = RequestMethod.GET)
	// public String dangky2(Model model) {
	// return "kiemtrathanhtoan";
	// }

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("users") Users users, BindingResult result, RedirectAttributes redirect,
			Model model, HttpSession session, HttpServletRequest request) {
		if (result.hasErrors()) {

			// gio hang
			com.spring.cart.Cart myCart = Utils.getCartInSession(request);
			model.addAttribute("listCart", myCart.getCartLines());
			model.addAttribute("total", myCart.getAmountTotal());
			model.addAttribute("quantity", myCart.getProductTotal());
			return "kiemtrathanhtoan";
		}

		Users user = new Users();
		user.setName(users.getName());
		user.setEmail(users.getEmail());
		user.setPassword(passwordEncoder.encode(users.getPassword()));
		user.setPhone_number(users.getPhone_number());
		user.setId_role(1);
		user.setActive(1);
		user.setAddress(users.getAddress());

		userRepoImpl.insert(user);
		session.setAttribute("user", user);
		System.err.println(user.getName());
		redirect.addFlashAttribute("success", "Saved contact successfully!");
		return "redirect:/CameraGiamSat/thanhtoan";
	}

	@RequestMapping(value = "/paypal/{codeOrder}/{total}")
	public String paypal(Model model, @PathVariable(value = "codeOrder") String codeOrder,
			@PathVariable(value = "total") int total) {
		model.addAttribute("codeOrder", codeOrder);
		model.addAttribute("total", total);
		return "paypal";
	}

	@RequestMapping(value = "/paypal-success")
	public String paypalSuccess() {
		return "paypal-success";
	}

	@RequestMapping(value = "/muahang", method = RequestMethod.POST)
	public ResponseEntity<?> muahang(Model model, HttpServletRequest request) throws MessagingException {

		// add to Order
		String codeOrder = request.getParameter("codeOrder");
		String idUser = request.getParameter("id_user");
		String nameCustomer = request.getParameter("nameCustomer");
		String email = request.getParameter("email");
		String idProcess = request.getParameter("idProcess");
		// String active = request.getParameter("active");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String total = request.getParameter("total");

		Order order = new Order();
		order.setCodeOrder(codeOrder);
		order.setNameCustomer(nameCustomer);
		order.setIdUser(Integer.parseInt(idUser));
		// order.setActive(Integer.parseInt(active));
		order.setAddress(address);
		order.setEmail(email);
		order.setPhoneNumber(phoneNumber);
		order.setTotal(Integer.parseInt(total));
		order.setIdProcess(Integer.parseInt(idProcess));

		orderRepoImpl.insertOrder(order);

		// format VND price
		double price = Double.parseDouble(total);
		Locale locale = new Locale("vi", "VN");
		Currency currency = Currency.getInstance("VND");

		DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
		df.setCurrency(currency);
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		numberFormat.setCurrency(currency);

		// send mail
		// MimeMessage message = sender.createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(message);
		// helper.setTo(email);
		// if (Integer.parseInt(idProcess) != 7) {
		// helper.setText("Bạn đã đặt mua đơn hàng: " + codeOrder + ". Tổng cộng: " +
		// numberFormat.format(price));
		// } else {
		// helper.setText("Bạn thanh toán đơn hàng: " + codeOrder + ". Số tiền: " +
		// numberFormat.format(price) + "\n"
		// + "Mọi thông tin xin liên hê: 0987654122 - Mr.Tung");
		// }
		// helper.setSubject("Cửa hàng Camera Giám Sát");
		// sender.send(message);

		return ResponseEntity.ok(new State("success", "Đã gửi giỏ hàng"));
	}
}