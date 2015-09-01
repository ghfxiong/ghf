package com.iamghf.wechat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.iamghf.wechat.common.FormatXmlProcess;
import com.iamghf.wechat.common.ReceiveXmlEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * 调用图灵机器人api接口，获取智能回复内容 
 * @author ghf
 * 
 */ 
public class TulingApiProcess {
	/** 
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果 
     * @param content 
     * @return 
     */  
    public String getTulingResult(ReceiveXmlEntity xmlEntity){   
    	String content = xmlEntity.getContent();
        String apiUrl = "http://www.tuling123.com/openapi/api?key=1519dfd4563a22e9d5bfb26fb873cc30&userid=60277&info=";  
    	//String apiUrl = "http://www.tuling123.com/openapi/wechatapi?key=1519dfd4563a22e9d5bfb26fb873cc30&userid=60277&info=";
        String param = "";  
        try {  
            param = apiUrl+URLEncoder.encode(content,"utf-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } //将参数转为url编码  
          
        /** 发送httpget请求 */  
        /*HttpGet request = new HttpGet(param);  
        String result = "";  
        try {  
            HttpResponse response = HttpClients.createDefault().execute(request);  
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity());  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  */
        String result="";
        try {
        	HttpClient client = new HttpClient();
        	GetMethod pmethod = new GetMethod(param);
			client.executeMethod(pmethod);
			//result = pmethod.getResponseBodyAsString();
			StringBuffer sb = new StringBuffer(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(pmethod.getResponseBodyAsStream(), "UTF-8")); 
			String s = "";  
	        while ((s = br.readLine()) != null) {  
	            sb.append(s);  
	        }
	        br.close();
	        result = sb.toString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}        
        /** 请求失败处理 */  
        if(null==result){  
            return "对不起，你说的话真是太高深了……";  
        }
        System.out.println(result);
        //JSONObject json = JSONObject.fromString(result);  
        //以code=100000为例，参考图灵机器人api文档  
       /* if(100000==json.getInt("code")){  
            result = json.getString("text");  
        }
        return result;*/  
        return buildTulingRetXml(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
    } 
    
    public String buildTulingRetXml(String to, String from,String result){
    	JSONObject json = JSONObject.fromString(result);  
    	FormatXmlProcess proc = new FormatXmlProcess();
    	int code = json.getInt("code");
    	if(100000== code ){
    		return proc.formatTextXmlAnswer(to, from, json.getString("text"));
    	}else if(302000 == code){
    		// {"code":302000,"text":"文字内容","list":[{"article":"标题 ","source":"来源 ","detailurl":"详情地址","icon":"图标地址"}]}
    		JSONArray arts = json.getJSONArray("list");
    		int count = arts!=null ? (arts.length()<11 ? arts.length(): 10) :0;
    		List<Map> lists = new ArrayList<Map>();
    		for(int i=0;i<count;i++){
    			Map art = new HashMap();
    			JSONObject obj = arts.getJSONObject(i);
    			art.put("Title", obj.getString("article"));
    			//art.put("Description", obj.getString(""));
    			art.put("PicUrl", obj.getString("icon"));
    			art.put("Url", obj.getString("detailurl"));
    			lists.add(art);
    		}
    		return proc.formatNewsXmlAnswer(to, from, count, lists);
    	}else {
    		return proc.formatTextXmlAnswer(to, from, "真搞不清！");
    	}
    }
	
	public static void main(String[] args) {
		TulingApiProcess proc = new TulingApiProcess();
		ReceiveXmlEntity xmlEntity = new ReceiveXmlEntity();
		xmlEntity.setFromUserName("iamghf");
		xmlEntity.setToUserName("ghfxiong");
		xmlEntity.setContent("娱乐新闻");
		System.out.println(proc.getTulingResult(xmlEntity));
		//proc.getTulingResult("你漂亮么 ");
	}

}
