package com.iamghf.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.cache.InterCfgCache;
import com.iamghf.web.beans.User;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.service.impl.BlogServiceImpl;
import com.iamghf.web.service.interfaces.IBlogService;

/**
 * @author ghf
 *
 */
@Controller
@RequestMapping("/cpage")
public class FreeMarkerController {
	private static transient Logger log = Logger.getLogger(FreeMarkerController.class);
	@RequestMapping("/index")  
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String id = request.getParameter("id");
		String code = request.getParameter("method");
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getInterCfg(code);
		
		if(null==bean){
			//jump to 404!
		}
		
		IBlogService bsv = new BlogServiceImpl();
		BArticleView art = bsv.getArticleById(Long.parseLong(id))[0];
        /*User user = new User();  
        user.setUserName("zhangsan");  
        user.setPassWord("1234");  
        List<User> users = new ArrayList<User>();  
        users.add(user);*/
        //Map uu = new HashMap();
        //uu.put("uName", "fffff");
        //uu.put("pwd", "123456");
        //return new ModelAndView("blogContent","list",uu);
        //return new ModelAndView("blogContent", "users", users);  
		return new ModelAndView("blogContent","artv",art);
    }  
}
