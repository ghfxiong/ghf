package com.ghf.core.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

class BaseControl extends HttpServlet{

	private static final Logger log = Logger.getLogger(BaseControl.class);
	private static final long serialVersionUID = -5422443992476902607L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		String pagesizeStr = req.getParameter("pagesize")==null ? (String)req.getAttribute("pagesize"):req.getParameter("pagesize");
	}

	
	
}
