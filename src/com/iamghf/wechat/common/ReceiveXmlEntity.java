package com.iamghf.wechat.common;
/** 
 * 接收到的微信xml实体类 
 * @author ghf
 * 
 */  
public class ReceiveXmlEntity {
	private String ToUserName="";   //开发者微信号 
    private String FromUserName="";  //接收方帐号
    private String CreateTime="";   //消息创建时间 （整型）
    private String MsgType="";  //text ,image ,voice ,video ,link ,event 
    private String MsgId="";  //消息id，64位整型 
    
    private String Event="";  //事件类型，subscribe(订阅)、unsubscribe(取消订阅) 
    private String EventKey="";  
    private String Ticket="";  
    private String Latitude="";  
    private String Longitude="";  
    private String Precision="";  
    
    private String PicUrl="";  //图片链接 
    private String MediaId="";  //媒体id，可以调用多媒体文件下载接口拉取数据
    private String Format="";  //格式
    private String ThumbMediaId ="";//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    
    private String Title="";  //消息标题 
    private String Description="";  //消息描述 
    private String Url="";  //消息链接 
    
    private String Location_X="";  
    private String Location_Y="";  
    private String Scale="";  
    private String Label="";  
    private String Content="";  // 	文本消息内容 
   
    private String Recognition="";
    
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}  
}
