package com.iamghf.web.beans.blog;

public class BlogInitBean {
	public BArticleView[] articles;
	public BTag[] tags;
	public BArticleView[] getArticles() {
		return articles;
	}
	public void setArticles(BArticleView[] articles) {
		this.articles = articles;
	}
	public BTag[] getTags() {
		return tags;
	}
	public void setTags(BTag[] tags) {
		this.tags = tags;
	}
	
}
