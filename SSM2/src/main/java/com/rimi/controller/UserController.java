package com.rimi.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rimi.bean.ApplyWayBean;
import com.rimi.bean.ConsultCategoryBean;
import com.rimi.bean.ConsultTimeBean;
import com.rimi.bean.ConsultWayBean;
import com.rimi.bean.EducationBean;
import com.rimi.bean.SourcesBean;
import com.rimi.bean.UserBean;
import com.rimi.service.UserService;

@Controller
@SessionAttributes("ub")
public class UserController {

	@Autowired
	UserService us;

	Md5 md5 = new Md5();

	@RequestMapping("/newlogin.do")
	public String jump(String userLoginName, String userPs, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		return login(userLoginName, userPs, request, response, session);
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] coos = request.getCookies();
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println(request.getSession().getAttribute("ub"));
		if (coos != null) {
			for (int i = 0; i < coos.length; i++) {
				if (coos[i].getName().equals("userLoginName")) {
					coos[i].setMaxAge(0);
					response.addCookie(coos[i]);
					System.out.println(coos[i].getName());
				}
				if (coos[i].getName().equals("userPs")) {
					coos[i].setMaxAge(0);
					response.addCookie(coos[i]);
					System.out.println(coos[i].getName());
				}
			}
			return "redirect:/newlogin.do";
		}
		return "redirect:/newlogin.do";
	}
	
	@RequestMapping("/login.do")
	public String login(String userLoginName, String userPs, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String code = (String) request.getSession().getAttribute("randCheckCode");
		String checkCode = request.getParameter("checkCode");
		Cookie[] coos = request.getCookies();
		if (coos == null) {
			return "page-login";
		} else {
			for (int i = 0; i < coos.length; i++) {
				if (coos[i].getName().equals("userLoginName")) {
					userLoginName = coos[i].getValue();
					checkCode = (String) request.getSession().getAttribute("randCheckCode");
				}
				if (coos[i].getName().equals("userPs")) {
					userPs = coos[i].getValue();
				}
			}
			if (userLoginName == null || userLoginName.equals("") || userPs == null || userPs.equals("")) {
				return "page-login";
			}
		}
		if (userLoginName == null || userLoginName.equals("")) {
			request.setAttribute("message", "用户名不能为空 ！");
			return "page-login";
		} else {
			if (checkCode == null || checkCode.equals("")) {
				request.setAttribute("userLoginName", userLoginName);
				return "page-login";
			} else if (!code.equals(checkCode)) {
				request.setAttribute("message", "验证码错误 ！");
				request.setAttribute("userLoginName", userLoginName);
				return "page-login";
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
							request.setAttribute("message", "该用户已经离职或者被禁用，无法登录 ！");
							return "page-login";
						} else {
							try {
								String remember = null;
								remember = request.getParameter("remember");
								System.out.println(remember);
								Cookie userLoginNameCookie;
								Cookie userPsCookie;

								if (remember == null || remember.equals(null)) {
									// 不论是否勾选记住密码，默认记住密码30分钟(已注释)
//									userLoginNameCookie = new Cookie("userLoginName", userLoginName);
//									userPsCookie = new Cookie("userPs", userPs);
//									userLoginNameCookie.setMaxAge(30 * 60);
//									userPsCookie.setMaxAge(30 * 60);
//									response.addCookie(userLoginNameCookie);
//									response.addCookie(userPsCookie);
//									System.out.println("cookie30分钟的保存到了");
								} else if (remember.equals("Checked")) {
									userLoginNameCookie = new Cookie("userLoginName", userLoginName);
									userPsCookie = new Cookie("userPs", userPs);
									userLoginNameCookie.setMaxAge(60 * 60 * 24 * 7);
									userPsCookie.setMaxAge(60 * 60 * 24 * 7);
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
								// zyc
								List<ConsultWayBean> cwb = us.getConsultWayByUserId();
								System.out.println(cwb.get(1).getCsWayName() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
								List<ApplyWayBean> awb = us.getApplyWayBean();
								List<EducationBean> ebs = us.getEducationList();
								List<UserBean> online = us.getOnlineTeachers();
								List<UserBean> offline = us.getOfflineTeachers();
								List<ConsultCategoryBean> ccbs = us.getConsultCategorys();
								List<ConsultTimeBean> ctbs = us.getConsultTimes();
								List<SourcesBean> sours = us.getSources();

								session.setAttribute("cwb", cwb);
								session.setAttribute("online", online);
								session.setAttribute("offline", offline);
								session.setAttribute("ebs", ebs);
								session.setAttribute("ub", ub);
								session.setAttribute("awb", awb);
								session.setAttribute("csway", cwb);
								session.setAttribute("ccbs", ccbs);
								session.setAttribute("ctbs", ctbs);
								session.setAttribute("sours", sours);

								// zyc-end
								session.setAttribute("ub", ub);
								return "redirect:index.do";
							} else {
								request.setAttribute("message", "身份信息错误，无法登录 ！");
								return "page-login";
							}
						}
					}
				}
			}
		}
	}
}