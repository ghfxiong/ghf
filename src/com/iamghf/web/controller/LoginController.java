/**
 * 
 */
package com.iamghf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamghf.web.BaseController;
import com.iamghf.web.beans.User;
import com.iamghf.web.service.interfaces.LoginService;

/**
 * µÇÂ½·½·¨
 * @author ghf
 * @date Nov 4, 2011
 */
@Controller
public class LoginController extends BaseController{

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/user/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String logon(String userName,String passWord){
		User user = null;
		try {
			user = loginService.getUser(userName, passWord);
		} catch (RuntimeException e) {
		}
		if(user!=null){
			//return "MyHome";
			return "goto_p_10001";
		}else{
			return "login";
		}
		//return "MyHome";
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	public String register(User user){
		loginService.addUser(user);
		//return "login";
		return "goto_p_10005";
	}
}
