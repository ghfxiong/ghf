package com.iamghf.wechat.common;

import com.iamghf.wechat.service.TulingApiProcess;

public class WechatProcess {
	/** 
     * 解析处理xml、获取智能回复结果（通过图灵机器人api接口） 
     * @param xml 接收到的微信数据 
     * @return  最终的解析结果（xml格式数据） 
     */  
    public String processWechatMag(String xml){  
        /** 解析xml数据 */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
          
        /** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */  
        String result = "";  
        if("text".endsWith(xmlEntity.getMsgType())){  
            result = new TulingApiProcess().getTulingResult(xmlEntity);    
            //result = new FormatXmlProcess().formatTextXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
        }else if("event".endsWith(xmlEntity.getMsgType())){
        	result = new FormatXmlProcess().formatTextXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), "欢迎关注！");
        }else {
        	result = new FormatXmlProcess().formatTextXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), "搞不清！");  
        }
        return result;  
    }  
	public static void main(String[] args) {
		
	}

}
