package com.iamghf.wechat.beans;

public class RetVoiceBean {
	private String MsgType = "voice";
	private String MediaId ;
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
