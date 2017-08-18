package com.rimi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rimi.bean.UserBean;
import com.rimi.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService us;

	@RequestMapping("/index.do")
	public String any(String userLoginName, String userPs,HttpServletRequest request) {
		Cookie[] coos = request.getCookies();
		if (coos != null) {
			for (int i = 0; i < coos.length; i++) {
				if (coos[i].getName().equals("userLoginName")) {
					userLoginName = coos[i].getValue();
				}
				if (coos[i].getName().equals("userPs")) {
					userPs = coos[i].getValue();
				}
			}
			UserBean ub = us.login(userLoginName, userPs);
			if (ub!= null) {
				if (ub.getUserRole() == 1) {
					return "user/index";
				} else if (ub.getUserRole() == 2) {
					return "stu/index";
				} else {
					return "page-login";
				}
			}else {
				return "page-login";
			}
		} else {
			return "page-login";
		}
	}

	@RequestMapping("/login.do")
	public String login(String userLoginName, String userPs, String remember, Model model,
			HttpServletResponse response) {

		UserBean ub = us.login(userLoginName, userPs);
		if (ub != null) {
			if (ub.getUserPs().equals(userPs)) {
				try {
					if (remember.equals("Checked")) {
						Cookie userLoginNameCookie = new Cookie("userLoginName", userLoginName);
						Cookie userPsCookie = new Cookie("userPs", userPs);
						userLoginNameCookie.setMaxAge(2 * 60);
						userPsCookie.setMaxAge(2 * 60);
						response.addCookie(userLoginNameCookie);
						response.addCookie(userPsCookie);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (ub.getUserRole() == 1) {
					return "user/index";
				} else if (ub.getUserRole() == 2) {
					return "stu/index";
				} else {
					return "page-login";
				}
			} else {
				model.addAttribute("psIsOk", "错误！");
				model.addAttribute("userLoginName", userLoginName);
				return "page-login";
			}
		} else {
			model.addAttribute("nameIsOk", "错误！");
			return "page-login";
		}
	}
}
