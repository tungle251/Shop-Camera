package com.spring;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.unbescape.html.HtmlEscape;

import com.spring.domain.Product;
import com.spring.repo.impl.CategoryRepoImpl;
import com.spring.repo.impl.OrderRepoImpl;
import com.spring.repo.impl.UserRepoImpl;
import com.spring.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCameraApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CategoryRepoImpl categoryRepoImpl;
	@Autowired
	private UserRepoImpl userRepoImpl;
	@Autowired
	private OrderRepoImpl orderRepoImpl;

	@Test
	public void testApp() {
		// System.err.println(passwordEncoder.matches("123",
		// "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu"));
		//
		// Product p = new Product();
		// p.setId_category(1);
		// p.setName("test");

		// System.err.println(productServiceImpl.add(p));
		// System.err.println(categoryRepoImpl.getCategoryByIdToot(1));
		// System.err.println(productServiceImpl.getProduct(1077).getImg());

		// System.err.println(userRepoImpl.findByMail2("tung22@gmail.com"));
		// if (userRepoImpl.findByMail("tung22@gmail.com").isPresent()) {
		// System.err.println("ok");
		// } else {
		// System.out.println("null");
		// }
		// System.err.println(userRepoImpl.findByMail("tung22@gmail.com"));

		// Users u = new Users();
		// u.setName("tung");
		// u.setEmail("email");
		//
		//
		// System.err.println(userRepoImpl.insert(u));

		// System.err.println(orderRepoImpl.getOrder(0));
		// System.err.println(orderRepoImpl.getListNews());
		//
		// Order order = new Order();
		// order.setCodeOrder("ybkajd");
		// order.setIdUser(3);
		// order.setActive(0);
		// order.setAddress("asdass");
		// order.setEmail("tung@gmail.com");
		// order.setPhoneNumber("asdas");
		// order.setTotal(50000);
		// order.setIdProcess(2);
		// orderRepoImpl.insertOrder(order);
		// System.out.println(order);

		// System.out.println("CURRENCY SYMBOL = " + df.getCurrencySymbol());
		// System.out.println("DECIMAL SEPARATOR = " + df.getDecimalSeparator());
		// System.out.println("GROUP SEPARATOR = " + df.getGroupingSeparator());
		// System.out.println("CURRENCY CODE = " + df.getInternationalCurrencySymbol());
		// System.out.println("DECIMAL PLACE = " +
		// numberFormat.getMaximumFractionDigits());
		// String sLP = ((DecimalFormat) numberFormat).toLocalizedPattern();

		Product p = productServiceImpl.getProduct(3);
		System.err.println(HtmlEscape.unescapeHtml(p.getDigital()));
	}

}
