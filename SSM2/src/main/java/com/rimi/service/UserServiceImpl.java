package com.rimi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rimi.bean.UserBean;
import com.rimi.dao.UserDao;

public class UserServiceImpl implements UserService {
	
	
	UserDao ud;
	
	@Override
	public UserBean login(String userLoginName, String userPs) {
		return ud.login(userLoginName, userPs);
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	

}
