package com.iamghf.web.beans.blog;

/**
 * 文章视图
 * @author ghf
 *
 */
public class BArticleView {
	/** */
	public long pid;
	public long uid;
	public String title;
	public String scont;
	public String thumb;
	public String content;
	public String time;
	public String rid;
	public int state;
	
	/** 作者名 */
	private String artname;
	
	/** tag 名称 */
	private String tagname;

	private BTag[] btag;
	private String url;
	/** 上一篇地址 */
	private String preurl;
	/** 下一篇地址*/
	private String nexturl;
	/** 文章类型 */
	private int arttype;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getArttype() {
		return arttype;
	}

	public void setArttype(int arttype) {
		this.arttype = arttype;
	}

	public String getPreurl() {
		return preurl;
	}

	public void setPreurl(String preurl) {
		this.preurl = preurl;
	}

	public String getNexturl() {
		return nexturl;
	}

	public void setNexturl(String nexturl) {
		this.nexturl = nexturl;
	}

	public BTag[] getBtag() {
		return btag;
	}

	public void setBtag(BTag[] btags) {
		this.btag = btags;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScont() {
		return scont;
	}

	public void setScont(String scont) {
		this.scont = scont;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String datetime) {
		this.time = datetime;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
	
}
