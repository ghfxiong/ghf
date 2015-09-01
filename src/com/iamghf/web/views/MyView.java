package com.iamghf.web.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class MyView extends AbstractUrlBasedView {
	// 默认内容类型
	private final static String CONTENT_TYPE = "text/html";

	private String responseContent;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	protected void renderMergedOutputModel(Map<String, Object> paramMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		response.setContentType(getContentType());
		// 写入 response 内容
		//response.getWriter().write(this.responseContent);
		//response.getWriter().close();
		//1 页面的路径是相对路径。sendRedirect可以将页面跳转到任何页面，不一定局限于本web应用中 浏览器地址栏变化
		//response.sendRedirect(request.getContextPath()+getUrl());
		//2 Servlet页面跳转的路径是相对路径。forward方式只能跳转到本web应用中的页面上。跳转后浏览器地址栏不会变化。 
		request.getRequestDispatcher(getUrl()).forward(request, response);
	}

	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	/**
	 * 设置 http response content
	 * @param responseContent
	 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
}
