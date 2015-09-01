package com.iamghf.wechat.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
/** 
 * 封装最终的xml格式结果 
 * @author ghf
 * 
 */ 
public class FormatXmlProcess {
	 /** 
     * 封装文字类的返回消息 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public String formatTextXmlAnswer(String to, String from, String content) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }  
    
    /** 
     * 封装文字类的返回消息 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public String formatImageXmlAnswer(String to, String from, String mediaId) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[");  
        sb.append(mediaId);  
        sb.append("]]></MediaId></Image><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }
    
    public String formatVoiceXmlAnswer(String to, String from, String mediaId ) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[voice]]></MsgType><Voice><MediaId><![CDATA[");  
        sb.append(mediaId);  
        sb.append("]]></MediaId></Voice><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }
    /**
     * 回复音乐消息
     * @param to 接收方帐号（收到的OpenID）
     * @param from 开发者微信号 
     * @param title 音乐标题 
     * @param Description 音乐描述 
     * @param MusicURL 音乐链接
     * @param HQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐 
     * @param ThumbMediaId 必传 缩略图的媒体id，通过上传多媒体文件，得到的id 
     * @return
     */
    public String formatMusicXmlAnswer(String to, String from,String title,String description,String musicURL,String hqMusicUrl, String thumbMediaId ) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml>")
        	.append("<ToUserName><![CDATA[").append(to).append("]]></ToUserName>")
        	.append("<FromUserName><![CDATA[").append(from).append("]]></FromUserName>")
        	.append("<CreateTime>").append(date.getTime()).append("</CreateTime>")
        	.append("<MsgType><![CDATA[music]]></MsgType>");
        sb.append("<Music>")
        	.append("<Title><![CDATA[").append(title).append("]]></Title>")
        	.append("<Description><![CDATA[").append(description).append("]]></Description>")
        	.append("<MusicUrl><![CDATA[").append(musicURL).append("]]></MusicUrl>")
        	.append("<HQMusicUrl><![CDATA[").append(hqMusicUrl).append("]]></HQMusicUrl>")
        	.append("<ThumbMediaId><![CDATA[").append(thumbMediaId).append("]]></ThumbMediaId>")
        	.append("<Description><![CDATA[").append(description).append("]]></Description>")
        .append("</Music>");
        sb.append("</xml>");  
        return sb.toString();  
    }
    /**
     * 回复图文消息
     * @param to
     * @param from
     * @param ArticleCount 必须   图文消息个数，限制为10条以内
     * @param Articles 必须   多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应 
     * @param Title 图文消息标题 
     * @param Description 图文消息描述 
     * @param PicUrl 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 
     * @param Url 点击图文消息跳转链接 
     * @return
     */
    public String formatNewsXmlAnswer(String to, String from, int count,List<Map> artList ) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml>")
        	.append("<ToUserName><![CDATA[").append(to).append("]]></ToUserName>")
        	.append("<FromUserName><![CDATA[").append(from).append("]]></FromUserName>")
        	.append("<CreateTime>").append(date.getTime()).append("</CreateTime>")
        	.append("<MsgType><![CDATA[news]]></MsgType>");
        sb.append("<ArticleCount>").append(count).append("</ArticleCount>");
        sb.append("<Articles>");
        for(Map art : artList){
        	sb.append("<item>")
	        	.append("<Title><![CDATA[").append(art.get("Title")).append("]]></Title>")
	        	.append("<Description><![CDATA[").append(art.get("Description")).append("]]></Description>")
	        	.append("<PicUrl><![CDATA[").append(art.get("PicUrl")).append("]]></PicUrl>")
	        	.append("<Url><![CDATA[").append(art.get("Url")).append("]]></Url>")
        	.append("</item>");
        }
        sb.append("<Articles>");
        sb.append("</xml>");  
        return sb.toString();  
    }
    
    
	public static void main(String[] args) {
		
	}

}
