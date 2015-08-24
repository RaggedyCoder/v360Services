package com.thevolume360.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	private static final Logger log = LoggerFactory
			.getLogger(AuthController.class);

	// Login form
	@RequestMapping("/login")
	public String login() {
		log.debug("login()");
		System.out.println("login");
		return "login";
	}

	// Login form with error
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		log.debug("loginError()");
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/login-logout")
	public String logoutSuccess(Model model) {
		log.debug("logoutSuccess()");

		model.addAttribute("logout", true);
		return "login";
	}
}
