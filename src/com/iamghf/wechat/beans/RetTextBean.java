package com.iamghf.wechat.beans;

public class RetTextBean extends RetBean{
	private String MsgType = "text";
	private String Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
