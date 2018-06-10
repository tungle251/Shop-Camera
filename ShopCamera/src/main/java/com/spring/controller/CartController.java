package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.spring.cart.Utils;
import com.spring.domain.Product;
import com.spring.service.ProductService;

@Controller
@RequestMapping("/CameraGiamSat")
public class CartController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/addCart" }, method = RequestMethod.POST)
	public String shoppingCartHandler(HttpServletRequest request, Model model, WebRequest webRequest) {
		int id = Integer.parseInt(webRequest.getParameter("id"));
		int qty = Integer.parseInt(webRequest.getParameter("qty"));
		com.spring.cart.Cart myCart = com.spring.cart.Utils.getCartInSession(request);
		Product p = productService.getProduct(id);
		myCart.addProduct(p, qty);
		return "redirect:/CameraGiamSat/shoppingCart";
	}

	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		com.spring.cart.Cart myCart = Utils.getCartInSession(request);
		model.addAttribute("listCart", myCart.getCartLines());
		model.addAttribute("total", myCart.getAmountTotal());
		model.addAttribute("quantity", myCart.getProductTotal());
		return "giohang2";
	}

	@RequestMapping(value = "/shoppingCartRemove/{id}")
	public String removeProductHandler(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		Product product = null;

		product = productService.getProduct(id);

		if (product != null) {

			com.spring.cart.Cart cartInfo = Utils.getCartInSession(request);

			cartInfo.removeProduct(product);
		}
		return "redirect:/CameraGiamSat/shoppingCart";
	}

//	@RequestMapping(value = { "/CameraGiamSat/update" }, method = RequestMethod.POST)
//	public String shoppingCartUpdateQty(HttpServletRequest request, Model model,
//			@ModelAttribute("cartForm") com.spring.cart.Cart cartForm) {
//		com.spring.cart.Cart cartInfo = Utils.getCartInSession(request);
//		cartInfo.updateQuantity(cartForm);
//		
//		return "redirect:/CameraGiamSat/shoppingCart";
//	}
}
