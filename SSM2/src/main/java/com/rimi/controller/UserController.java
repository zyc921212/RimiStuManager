package com.rimi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rimi.bean.ApplyWayBean;
import com.rimi.bean.ConsultBean;
import com.rimi.bean.ConsultCategoryBean;
import com.rimi.bean.ConsultTimeBean;
import com.rimi.bean.ConsultWayBean;
import com.rimi.bean.EducationBean;
import com.rimi.bean.NewrecordBean;
import com.rimi.bean.PageBean;
import com.rimi.bean.SourcesBean;
import com.rimi.bean.StudentInfoBean;
import com.rimi.bean.UserBean;
import com.rimi.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService us;

/*	@RequestMapping("/index.do")
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
	}*/

	@RequestMapping("/login.do")
	public String login(String userLoginName, String userPs, String remember, Model model,
			HttpServletResponse response,HttpSession session) {

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
					//zyc
					List<ConsultWayBean> cwb=us.getConsultWayByUserId();
					System.out.println(cwb.get(1).getCsWayName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					List<ApplyWayBean> awb=us.getApplyWayBean();
					List<EducationBean> ebs=us.getEducationList();
					List<UserBean> online=us.getOnlineTeachers();
					List<UserBean> offline=us.getOfflineTeachers();
					List<ConsultCategoryBean> ccbs=us.getConsultCategorys();
					List<ConsultTimeBean> ctbs=us.getConsultTimes();
					List<SourcesBean> sours=us.getSources();
					
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
				
					//zyc-end
					return "redirect:index.do";
				} else {
					return "page-login";
				}
			} else {
				model.addAttribute("psIsOk", "密码错误！");
				model.addAttribute("userLoginName", userLoginName);
				return "page-login";
			}
		} else {
			model.addAttribute("nameIsOk", "用户名错误！");
			return "page-login";
		}
	}
}