package com.iamghf.wechat.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iamghf.wechat.common.WechatProcess;

public class WechatAction extends HttpServlet{
	private static final long serialVersionUID = -6119415553389212655L;

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  
        resp.setCharacterEncoding("UTF-8");  
        //resp.setHeader("WWW-Authenticate", "Basic realm=\"USER LOGIN\"");
       // resp.setIntHeader("Location", "www.baidu.com");
        System.out.println(req.getParameter("test"));
        
        /** 读取接收到的xml消息 */  
        StringBuffer sb = new StringBuffer();  
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }
        br.close();
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据  
        System.out.println(xml);
  
        String result = "";  
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */  
        String echostr = req.getParameter("echostr");  
        if (echostr != null && echostr.length() > 1) {  
            result = echostr;  
        } else {  
            //正常的微信处理流程  
            result = new WechatProcess().processWechatMag(xml);  
        }  
        System.out.println(result);
        try {  
            OutputStream os = resp.getOutputStream();  
            os.write(result.getBytes("UTF-8"));  
            os.flush();  
            os.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}


	public static void main(String[] args) {
		WechatAction act = new WechatAction();
		//String xml ="<xml><ToUserName><![CDATA[gh_adc0cdf4e23f]]></ToUserName><FromUserName><![CDATA[oLK0Ej7sJWKk0tm5TfteYKnOeyUk]]></FromUserName><CreateTime>1426000502</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你漂亮吗]]></Content><MsgId>6124625520373892532</MsgId></xml>";
		String xml = "<xml><ToUserName><![CDATA[gh_adc0cdf4e23f]]></ToUserName><FromUserName><![CDATA[oLK0Ej7sJWKk0tm5TfteYKnOeyUk]]></FromUserName><CreateTime>1426079532</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[搞笑]]></Content><MsgId>6124964951639429915</MsgId></xml>";
		String result = new WechatProcess().processWechatMag(xml);
	}

}
