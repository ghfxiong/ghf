package com.iamghf.web.service.interfaces;

import java.util.Map;

import net.sf.json.JSONArray;

import com.iamghf.web.beans.blog.BArticle;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.beans.blog.BAuthor;
import com.iamghf.web.beans.blog.BCategory;

public interface IBlogService {
	/**
	 * @return
	 */
	public BArticle[] getArticle();
	
	public BAuthor[] getAuthor();
	
	public BCategory[] getTags() throws Exception;
	
	public BArticleView[] getArticleById(long articleId)throws Exception;
	
	/** 获取最新文章*/
	public BArticleView[] getArticleTopNew()throws Exception;
	
	/** 点击排行*/
	public BArticleView[] getArticleClick()throws Exception;
	
	/** 推荐文章*/
	public BArticleView[] getArticleRecom()throws Exception;
	
	public long getArticleCount()throws Exception;
	
	public void publishArt(BArticleView artView)throws Exception;
	
	public BArticleView[] getArticleNewPage(Map pageMap)throws Exception;
}
