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
	public String jump(String userLoginName, String userPs, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, String remember) {
		Cookie[] coos = request.getCookies();
		if (coos == null) {
			return "page-login";
		} else {
			return login(userLoginName, userPs, request, response, session, remember);
		}
	}

	@RequestMapping("/login.do")
	public String login(String userLoginName, String userPs, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, String remember) {
		if (userLoginName == null || userLoginName.equals("")) {
			Cookie[] coos = request.getCookies();
			if (coos == null) {
				return "page-login";
			} else {
				for (int i = 0; i < coos.length; i++) {
					if (coos[i].getName().equals("userLoginName")) {
						userLoginName = coos[i].getValue();
					}
					if (coos[i].getName().equals("userPs")) {
						userPs = coos[i].getValue();
					}
				}
				if (userLoginName == null || userLoginName.equals("")) {
					return "page-login";
				} else {
					return login(userLoginName, userPs, request, response, session, remember);
				}
			}
		} else {
			UserBean ub = us.login(userLoginName, userPs);
			if (ub == null) {
				request.setAttribute("nameIsOk", "用户名不存在！");
				return "page-login";
			} else {
				if (!ub.getUserPs().equals(userPs)) {
					request.setAttribute("psIsOk", "密码错误！");
					request.setAttribute("userLoginName", userLoginName);
					return "page-login";
				} else {
					if (ub.getUserState() == 2 || ub.getUserJobState() == 3) {
						request.setAttribute("userState", "该用户已经离职或者被禁用，无法登录 ！");
						return "page-login";
					} else {
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
							return "user/index";
						} else if (ub.getUserRole() == 2) {
							session.setAttribute("ub", ub);
							return "stu/index";
						} else {
							request.setAttribute("userState", "身份信息错误，无法登录 ！");
							return "page-login";
						}
					}
				}
			}
		}
	}
}