package com.iamghf.web.service.interfaces;

import com.iamghf.web.beans.User;

public interface LoginService {
	public User getUser(String userName,String password);
	public void addUser(User user);
}
