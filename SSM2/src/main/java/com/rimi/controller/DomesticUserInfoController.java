package com.rimi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rimi.bean.SexBean;
import com.rimi.bean.UserBean;
import com.rimi.service.UserManagerService;

@Controller
public class DomesticUserInfoController {

	@Autowired
	private UserManagerService userMs;
	
	@RequestMapping("/UserInfo.do")
	public String lookInfo(String InfoUserId,Model model,HttpSession session) {
		//性别集合
		List<SexBean> sexList = userMs.selectSex();
		model.addAttribute("infoSex",sexList);
		int userId =0;
		if(InfoUserId !=null && !"".equals(InfoUserId)) {
			userId = Integer.parseInt(InfoUserId);
		}
		UserBean ub = userMs.getInfoById(userId);
		if(ub!=null) {
			session.setAttribute("InfoUb", ub);
			return "stu/user";
		}
		return "stu/user";
	}
	
	
	@RequestMapping("/updateUserD.do")
	public String updateUser(HttpServletRequest request,HttpSession session) {
		String userName = request.getParameter("userName");
		String userSex1 = request.getParameter("userSex");
		String userAge1 = request.getParameter("userAge");
		String userEmail = request.getParameter("userEamil");
		String userPhone = request.getParameter("userPhone");
		String userTel = request.getParameter("userTel");
		String userQq = request.getParameter("userQq");
		UserBean ub =(UserBean)session.getAttribute("InfoUb");
		//int userId = 0;
		//if(ub!=null) {
			int userId = ub.getUserId();
			int userSex = 0;
			int userAge = 0;
			if(userSex1 !=null && !"".equals(userSex1)) {
				userSex = Integer.parseInt(userSex1);
			}
			if(userAge1 !=null && !"".equals(userAge1)) {
				userAge = Integer.parseInt(userAge1);
			}else {
				userAge = ub.getUserAge();
			}
			UserBean ub1  = new UserBean();
			ub1.setUserId(userId);
			if(userName !=null && !"".equals(userName)) {
				ub1.setUserName(userName);
			}
			if(userSex>0) {
				ub1.setUserSex(userSex);
			}
			if(userAge>=0 ) {
				ub1.setUserAge(userAge);
			}
			if(userEmail !=null && !"".equals(userEmail)) {
				ub1.setUserEmail(userEmail);
			}
			if(userPhone !=null && !"".equals(userPhone)) {
				ub1.setUserPhone(userPhone);
			}
			if(userTel !=null && !"".equals(userTel)) {
				ub1.setUserTel(userTel);
			}
			if(userQq !=null && !"".equals(userQq)) {
				ub1.setUserQq(userQq);
			}
			boolean isok = userMs.updateUserById(ub1);
			if(isok) {
				return "redirect:/UserInfo.do?InfoUserId="+userId;
			}else {
				return "redirect:/UserInfo.do?InfoUserId="+userId;
			}
//		}
//		return "redirect:/lookInfo.do?InfoUserId="+userId;
	}

}
