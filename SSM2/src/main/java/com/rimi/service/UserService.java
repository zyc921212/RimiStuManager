package com.rimi.service;

import com.rimi.bean.UserBean;
import com.rimi.dao.UserDao;

public interface UserService {
	public UserBean login(String userLoginName,String userPs);
}
