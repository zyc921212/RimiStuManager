package com.rimi.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.rimi.bean.UserBean;

public interface UserManagerDao {
	
	//查询最大ID
	public int maxId();
	
	//验证用户登录名是否存在
	public UserBean checkLoginName(String userLoginName);
	
	//添加用户
	public boolean addUser(UserBean ub);
	
	//查询用户
	public List queryUser(Map map);
	
	//通过id查看用户详细信息
	public UserBean getInfoById(int userId);
	
	//根据Id修改用户信息
	public boolean editUser(UserBean ub);
	
	//根据Id删除用户
	public boolean delUserById(int userId);
	
	//批量删除用户
	public boolean delAllUser(@Param("set")Set set);
	
	//查询性别表
	public List selectSex();
	
	//查询状态表
	public List selectState();
	
	//查询职业表
	public List selectJob();
	
	//查询角色表
	public List selectRole();
	
	//查询工作状态表
	public List selectJobState();
	
	//修改个人资料
	public boolean updateUserById(Map map);
	
	//修改密码
	public boolean editPassword(Map map);
}
