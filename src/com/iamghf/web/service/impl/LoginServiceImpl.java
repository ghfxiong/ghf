package com.iamghf.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iamghf.web.beans.User;
import com.iamghf.web.service.interfaces.LoginService;
@Service
public class LoginServiceImpl implements LoginService{
	private static final Logger log = Logger.getLogger(LoginServiceImpl.class);
	@Override
	public User getUser(String userName, String password) {
		User user = new User();
		if("111".equals(password)&&"ghf".equals(userName)){
			user.setAge(11);
			user.setFirstName("xiong");
			user.setUserName("ghf");
			user.setPassWord("111");
		}
		return user;
	}

	@Override
	public void addUser(User user) {
		log.info("add user success");
	}

}
