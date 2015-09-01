package com.iamghf.web.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class MyView extends AbstractUrlBasedView {
	// Ĭ����������
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
		// д�� response ����
		//response.getWriter().write(this.responseContent);
		//response.getWriter().close();
		//1 ҳ���·�������·����sendRedirect���Խ�ҳ����ת���κ�ҳ�棬��һ�������ڱ�webӦ���� �������ַ���仯
		//response.sendRedirect(request.getContextPath()+getUrl());
		//2 Servletҳ����ת��·�������·����forward��ʽֻ����ת����webӦ���е�ҳ���ϡ���ת���������ַ������仯�� 
		request.getRequestDispatcher(getUrl()).forward(request, response);
	}

	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	/**
	 * ���� http response content
	 * @param responseContent
	 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
}
