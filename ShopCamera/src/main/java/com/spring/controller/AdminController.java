package com.spring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.Product;
import com.spring.repo.impl.CategoryRepoImpl;
import com.spring.service.impl.CategoryServiceImpl;
import com.spring.service.impl.ProductServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private CategoryRepoImpl categoryRepoImpl;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String loginView(@RequestParam(name = "error", required = false) String error, Model model) {
		if (error != null) {
			String message = "username or password is not correct";
			model.addAttribute("loginError", message);

		}
		return "admin/login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, Principal principal) {
		model.addAttribute("name", principal.getName());
		return "admin/index";
	}

	@RequestMapping(value = "/danhsachsanpham", method = RequestMethod.GET)
	public String getListProduct(HttpServletRequest request) {
		request.getSession().setAttribute("productlist", null);
		return "redirect:/admin/danhsachsanpham/page/1";
	}

	@RequestMapping(value = "/danhsachsanpham/page/{pageNumber}", method = RequestMethod.GET)
	public String getListProduct2(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		int pagesize = 10;
		List<Product> list = productServiceImpl.getAll();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("productlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/danhsachsanpham/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("list", pages);
		return "admin/danhsachsanpham";
	}

	@RequestMapping("/themsanpham")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCate", categoryRepoImpl.getCategoryByIdToot(0));
		model.addAttribute("categoryRepoImpl", categoryRepoImpl);
		model.addAttribute("selected", 3);
		return "admin/themsanpham";
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") @Valid Product product, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "/admin/themsanpham";
		}
		productServiceImpl.add(product);
		redirect.addFlashAttribute("success", "Saved contact successfully!");
		return "redirect:/admin/danhsachsanpham";
	}

	
}
