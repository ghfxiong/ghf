package com.iamghf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController extends BaseController {
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public ModelAndView hello(){
		
		ModelAndView mv = new ModelAndView();
		// ���ģ������ �����������POJO����
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("hello");
		return mv;
	}

	public ModelAndView handleRequest(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		// 1���ռ���������֤����
		// 2���󶨲������������
		// 3�������������ҵ��������ҵ����
		// 4��ѡ����һ��ҳ��
		ModelAndView mv = new ModelAndView();
		// ���ģ������ �����������POJO����
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("hello");
		return mv;
	}
}
