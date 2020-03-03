package com.wangziping.shop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangziping.shop.service.UserService;

@Controller
public class UserController {

	@Reference(timeout = 2000, version = "1.0.0")
	private UserService userService;

	@RequestMapping("toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("login")
	public String login(String userName, String password) {
		if (userService.login(userName, password)) {
			return "redirect:/";
		} else {

			return "login";
		}
	}

}
