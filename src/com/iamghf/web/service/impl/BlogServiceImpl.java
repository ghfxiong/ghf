package com.iamghf.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ghf.bean.Pagination;
import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.utils.common.ServiceFactory;
import com.iamghf.web.beans.blog.BArticle;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.beans.blog.BAuthor;
import com.iamghf.web.beans.blog.BCategory;
import com.iamghf.web.beans.blog.BTag;
import com.iamghf.web.beans.blog.BTagRelation;
import com.iamghf.web.common.WebActionUtil;
import com.iamghf.web.common.WebConstant;
import com.iamghf.web.dao.TableSql;
import com.iamghf.web.dao.impl.BlogDao;
import com.iamghf.web.service.interfaces.IBlogService;
@Service
public class BlogServiceImpl implements IBlogService{
	private static BlogDao dao = new BlogDao();
	private BlogDao getDao(){
		if(null == dao){
			dao = new BlogDao();
		}
		return dao;
	}
	@Override
	public BArticle[] getArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAuthor[] getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BCategory[] getTags() throws Exception {
		/*BCategory[] categorys = getDao().getTags();
		JSONArray array = new JSONArray();
		if(!ArrayUtils.isEmpty(categorys)){
			for(BCategory category : categorys){
				JSONObject tag = new JSONObject();
				tag.put("id", category.getId());
				tag.put("url", basePath+"/jump?method=p_10001&tagid="+i);
				tag.put("name", "tag"+i);
			}
		}*/
		return getDao().getTags();
	}
	/**
	 * 
	 */
	public BArticleView[] getArticleById(long articleId) throws Exception {
		BArticle article = getDao().getArticleById(articleId)[0];
		long pid = article.getPid();
		List<BArticleView> arts = new ArrayList<BArticleView>();
		if(pid>0){
			BArticleView artV = new BArticleView();
			artV.setPid(pid);
			artV.setTitle(article.getTitle());
			artV.setThumb(article.getThumb());
			artV.setContent(article.getContent());
			artV.setScont(article.getScont());
			artV.setArtname(article.getName());
			artV.setTime(article.getTime());
			
			BTagRelation[] tags = getDao().getTagRelat(-1, -1, pid);
			BTag[] btags = new BTag[tags.length];
			for(int i=0;i<tags.length;i++){
				btags[i] = new BTag();
				btags[i].setId(tags[i].getCid());
				btags[i].setName(tags[i].getCname());
			}
			artV.setBtag(btags);
			arts.add(artV);
		}
		return arts.toArray(new BArticleView[]{});
	}
	/** 获取最新文章*/
	public BArticleView[] getArticleTopNew()throws Exception{
		List<BArticleView> arts = new ArrayList<BArticleView>();
		BArticle[] articles = getDao().getArticleTopNew();
		for(BArticle bart : articles){
			BArticleView art = new BArticleView();
			/*art.setPid(bart.getPid());
			art.setTitle(bart.getTitle());
			art.setArttype(WebConstant.ARTICLE.ARTICLE_TYPE_NEW_THUMB);
			art.setScont(bart.getScont());
			art.setThumb(bart.getThumb());
			art.setUrl("/jump?method=p_10006&id="+bart.getPid());*/
			this.convertArtView(art, bart, 1);
			arts.add(art);
		}
		return arts.toArray(new BArticleView[]{});
	}
	/** 点击排行*/
	public BArticleView[] getArticleClick()throws Exception{
		List<BArticleView> arts = new ArrayList<BArticleView>();
		
		return arts.toArray(new BArticleView[]{});
	}
	/** 推荐文章*/
	public BArticleView[] getArticleRecom()throws Exception{
		List<BArticleView> arts = new ArrayList<BArticleView>();
		BArticle[] articles = getDao().getArticleRandom();
		for(BArticle bart : articles){
			BArticleView art = new BArticleView();
			/*art.setPid(bart.getPid());
			art.setTitle(bart.getTitle());
			art.setArttype(WebConstant.ARTICLE.ARTICLE_TYPE_RECOM_THUMB);
			art.setScont(bart.getScont());
			art.setThumb(bart.getThumb());
			art.setUrl("/jump?method=p_10006&id="+bart.getPid());*/
			this.convertArtView(art, bart, 1);
			arts.add(art);
		}
		return arts.toArray(new BArticleView[]{});
	}
	@Override
	public void publishArt(BArticleView artView) throws Exception {
		artView.setArtname("ghf");
		if(StringUtils.isEmpty(artView.getScont())){
			String scont = artView.getContent().length()>50? artView.getContent().substring(0,50):artView.getContent();
			artView.setScont(scont);
		}		
		BArticle article = new BArticle();
		this.convertArtView(artView, article, 2);
		
		getDao().saveArticle(article);
	}
	
	public BArticleView[] getArticleNewPage(Map pageMap)throws Exception{
		StringBuffer condtion = new StringBuffer();
		//condtion.append(" where `name` like '%")
		condtion.append(" ORDER BY pid DESC ");
		
		StringBuffer sql = new StringBuffer();
		sql.append(TableSql.buildPageSql("b_article", "PID", condtion.toString(), pageMap));
		
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List<BArticle> list = dao.query(BArticle.class, sql.toString());
		List<BArticleView> vlist = new ArrayList<BArticleView>(list.size());
		for(BArticle article:list){
			BArticleView view = new BArticleView();
			this.convertArtView(view, article, 1);
			vlist.add(view);
		}
		return vlist.toArray(new BArticleView[]{});
	}
	
	private void convertArtView(BArticleView artView,BArticle article ,int type){
		if(type==1){
			// BArticle ----> BArticleView
			artView.setPid(article.getPid());
			artView.setTitle(article.getTitle());
			artView.setArttype(WebConstant.ARTICLE.ARTICLE_TYPE_RECOM_THUMB);
			artView.setScont(article.getScont());
			if(StringUtils.isEmpty(article.getThumb())){
				artView.setThumb("/resoure/blog/images/01.jpg");
			}else{
				artView.setThumb(article.getThumb());
			}
			artView.setUrl("/jump?method=p_10006&id="+article.getPid());
			artView.setArtname(article.getName());
			artView.setTime(article.getTime());
		}else if(type ==2){
			// BArticleView ----> BArticle
			article.setTitle(artView.getTitle());
			if(artView.getPid()>0){
				article.setPid(artView.getPid());
			}
			article.setUid(1000);
			article.setContent(artView.getContent());
			article.setName(artView.getArtname());
			article.setScont(artView.getScont());
			article.setState(1);
			article.setThumb(artView.getThumb());
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			article.setTime(sf.format(date));
		}
	}
	@Override
	public long getArticleCount() throws Exception {
		return getDao().getArticleCount();
	}
	
	public static void main(String[] args)throws Exception{
		BlogServiceImpl bsv = new BlogServiceImpl();
		List<BCategory> list = new ArrayList<BCategory>();
		for(int i=0;i<3;i++){
			BCategory b1 = new BCategory();
			b1.setName("fff"+i);
			b1.setType(3);
			list.add(b1);
		}
		bsv.getDao().save(list.toArray(new BCategory[]{}));
	}
}
