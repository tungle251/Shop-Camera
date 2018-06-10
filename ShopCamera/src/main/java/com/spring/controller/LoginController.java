package com.spring.controller;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.spring.domain.State;
import com.spring.domain.Users;
import com.spring.repo.impl.UserRepoImpl;

@RestController
public class LoginController {
	// @Autowired
	// private ProductService productService;
	// @Autowired
	// private ProductServiceImpl productServiceImpl;
	// @Autowired
	// private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private UserRepoImpl userRepoImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender sender;

	@RequestMapping(value = "/CameraGiamSat/loginIndex", method = RequestMethod.POST)
	public ResponseEntity<?> testJSON3(WebRequest webRequest, HttpSession session) {
		String mail = webRequest.getParameter("email");
		String password = webRequest.getParameter("password");
		Optional<Users> users = userRepoImpl.findByMail(mail);
		if (!users.isPresent()) {
			return ResponseEntity.ok(new State("error", "Sai tài khoản hoặc mật khẩu"));
		} else if (users.get() != null && passwordEncoder.matches(password, users.get().getPassword())
				&& users.get().getActive() == 1) {
			session.setAttribute("user", users.get());
			return ResponseEntity.ok(new State("success", "Đăng nhập thành công"));
		}
		return ResponseEntity.ok(new State("error", "Sai tài khoản hoặc mật khẩu"));
	}

	@RequestMapping("/CameraGiamSat/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo("14130146@st.hcmuaf.edu.vn");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}
}
