package com.iamghf.wechat.beans;

public class RetNewsBean {
	private String MsgType = "music";
	private int ArticleCount ;
	private ArticleBean[] Articles;
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = Articles!=null ? Articles.length : 0;
	}
	public ArticleBean[] getArticles() {
		return Articles;
	}
	public void setArticles(ArticleBean[] articles) {
		Articles = articles;
	}
	
}
