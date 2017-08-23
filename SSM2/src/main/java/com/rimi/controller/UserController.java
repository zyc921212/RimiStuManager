package com.rimi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rimi.bean.UserBean;
import com.rimi.service.UserService;

@Controller
@SessionAttributes("ub")
public class UserController {

	@Autowired
	UserService us;

	@RequestMapping("/index.do")
	public String any(String userLoginName, String userPs, HttpServletRequest request,HttpSession session) {
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
			if (ub != null) {
				if (ub.getUserState() == 1 && (ub.getUserJobState() == 1 || ub.getUserJobState() == 2)) {
					if (ub.getUserRole() == 1) {
						session.setAttribute("ub", ub);
						request.setAttribute("nameIsOk", "");
						return "user/index";
					} else if (ub.getUserRole() == 2) {
						session.setAttribute("ub", ub);
						request.setAttribute("nameIsOk", "");
						return "stu/index";
					} else {
						return "page-login";
					}
				} else {
					request.setAttribute("userState", "该用户已经离职或者被禁用，无法登录 ！");
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
	public String login(String userLoginName, String userPs, String remember, Model model, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) {
		request.setAttribute("nameIsOk","");
		UserBean ub = us.login(userLoginName, userPs);
		if (ub != null) {
			if (ub.getUserPs().equals(userPs)) {
				if (ub.getUserState() == 1 && (ub.getUserJobState() == 1 || ub.getUserJobState() == 2)) {
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
						session.setAttribute("ub", ub);
						request.setAttribute("nameIsOk", "");
						return "user/index";
					} else if (ub.getUserRole() == 2) {
						session.setAttribute("ub", ub);
						request.setAttribute("nameIsOk", "");
						return "stu/index";
					} else {
						return "page-login";
					}
				} else {
					model.addAttribute("userState", "该用户已经离职或者被禁用，无法登录 ！");
					return "page-login";
				}
			} else {
				model.addAttribute("psIsOk", "密码错误！");
				request.setAttribute("userLoginName", userLoginName);
				return "page-login";
			}
		} else if(userLoginName == null || userLoginName.equals("")){
			request.setAttribute("nameIsOk", "用户名不能为空！");
			return any(userLoginName, userPs, request,session);
		} else {
			request.setAttribute("nameIsOk", "用户名不存在！");
			return any(userLoginName, userPs, request,session);
		}
	}
}