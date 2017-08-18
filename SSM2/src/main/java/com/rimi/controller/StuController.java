package com.rimi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StuController {

	@RequestMapping("/login.do")
	public String login() {
		return "stu/index";
	}
	
}
