package com.iamghf.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.cache.InterCfgCache;
import com.iamghf.web.BaseController;
import com.iamghf.web.beans.UserSession;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.common.WebConstant;
import com.iamghf.web.service.impl.BlogServiceImpl;
import com.iamghf.web.service.interfaces.IBlogService;
@Controller
public class MainController extends BaseController{
	private static transient final Logger log = Logger.getLogger(MainController.class);
	@RequestMapping(value="/doAction",method = RequestMethod.GET)
	public void doAction(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");  //���ﲻ���ñ����������
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("method");
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getInterCfg(code);
		
		if(null==bean){
			//jump to 404!
			this.jump404(session, request, response);
		}
		
		String implClass = bean.getImplclass();
		String implFunc = bean.getImplfunc();
		log.debug("implClass:"+implClass+"------"+implFunc);
		log.debug("impl-method:"+implFunc);
		
		Class clazz = Class.forName(implClass);
		Method method = clazz.getMethod(implFunc, HttpServletRequest.class,HttpServletResponse.class);
		method.getReturnType();
		Object retObj = method.invoke(clazz.newInstance(),request, response);
		if(retObj!=null){
			PrintWriter out = response.getWriter();
			if(log.isDebugEnabled()){
				log.debug(retObj);
			}
			out.write(retObj.toString());
		}
	}
	
	@RequestMapping(value="/jump",method = RequestMethod.GET)
	public void doJump(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		if(log.isDebugEnabled()){
			log.debug("the request is : "+request.getRequestURL());
		}
		String code = request.getParameter("method");
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getCfgPage(code);
		
		if(null==bean){
			//jump to 404!
			//request.getRequestDispatcher(WebConstant.WEB_JUMP_FLAG.JUMP_404_URL).forward(request, response);
			this.jump404(session, request, response);
		}else{
			String implClass = bean.getImplclass();
			String implFunc = bean.getImplfunc();
			log.debug("implClass:"+implClass+"------"+implFunc);
			log.debug("impl-method:"+implFunc);
			request.getRequestDispatcher(implClass).forward(request, response);
		}
	}
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public void publish(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSession us = (UserSession)session.getAttribute("user_info");
		if(null == us){
			//jump 404
			this.jump404(session, request, response);
		}else{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder(1024);
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			if(log.isDebugEnabled()){
				//log.debug("post data is ---- "+URLDecoder.decode(sb.toString(), "UTF-8")	);
				log.debug("post data is ---- "+sb.toString());
			}
			JSONObject obj = JSONObject.fromString(sb.toString());
			BArticleView view = new BArticleView();
			view.setTitle(obj.getString("title"));
			view.setThumb(obj.getString("thumb"));
			view.setScont(obj.getString("scont"));
			view.setContent(obj.getString("content"));
			IBlogService bsv = new BlogServiceImpl();
			bsv.publishArt(view);
		}
	}
	
	private void jump404(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
		//request.getRequestDispatcher(WebConstant.WEB_JUMP_FLAG.JUMP_404_URL).forward(request, response);
		response.sendRedirect(request.getContextPath()+WebConstant.WEB_JUMP_FLAG.JUMP_404_URL);
	}
}
