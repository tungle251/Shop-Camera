//package com.spring.controller;
//
//import java.util.Optional;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.spring.domain.PersonForm;
//import com.spring.domain.State;
//import com.spring.domain.Users;
//import com.spring.repo.impl.CategoryRepoImpl;
//import com.spring.repo.impl.ProductRepoImpl;
//import com.spring.repo.impl.UserRepoImpl;
//import com.spring.service.CategoryService;
//import com.spring.service.ProcessingService;
//import com.spring.service.ProductService;
//import com.spring.service.impl.ProductServiceImpl;
//
//@Controller
//public class WebController {
//	@Autowired
//	private ProcessingService processService;
//	@Autowired
//	private CategoryService categoryService;
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private ProductServiceImpl productServiceImpl;
//	@Autowired
//	private ProductRepoImpl productRepoImpl;
//	@Autowired
//	private CategoryRepoImpl categoryRepoImpl;
//
//	@Autowired
//	private UserRepoImpl userRepoImpl;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	
//}