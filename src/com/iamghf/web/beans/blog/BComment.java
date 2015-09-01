package com.iamghf.web.beans.blog;

public class BComment {
	private static final String tableName = "b_article";
	private long commentid;
	private long artid;
	private long cname;
	private long content;
	private long recontent;
	public long getCommentid() {
		return commentid;
	}
	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}
	public long getArtid() {
		return artid;
	}
	public void setArtid(long artid) {
		this.artid = artid;
	}
	public long getCname() {
		return cname;
	}
	public void setCname(long cname) {
		this.cname = cname;
	}
	public long getContent() {
		return content;
	}
	public void setContent(long content) {
		this.content = content;
	}
	public long getRecontent() {
		return recontent;
	}
	public void setRecontent(long recontent) {
		this.recontent = recontent;
	}
	
	
}
