package com.iamghf.wechat.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iamghf.web.BaseController;
import com.iamghf.wechat.common.WechatProcess;
@Controller
public class WechatController extends BaseController{
	
	@RequestMapping(value="/wechat",method=RequestMethod.POST)
	public void publish(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws Exception{
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
}
