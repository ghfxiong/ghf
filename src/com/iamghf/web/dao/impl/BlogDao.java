package com.iamghf.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ghf.core.dao.connections.DbConnUtil;
import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.core.dao.utils.DbUtils;
import com.ghf.utils.common.ServiceFactory;
import com.iamghf.web.beans.blog.BArticle;
import com.iamghf.web.beans.blog.BCategory;
import com.iamghf.web.beans.blog.BTagRelation;

public class BlogDao {
	private static transient Logger log = Logger.getLogger(BlogDao.class);
	/*private static DbConnUtil conn ;
	private DbConnUtil getConn() throws Exception, IllegalAccessException, ClassNotFoundException{
		if(conn==null){
			conn =  (DbConnUtil)Class.forName(DbUtils.jdbc).newInstance();
		}
		return conn;
	}*/
	
	public BCategory[] getTags() throws Exception{
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuilder sql = new StringBuilder(256);
		sql.append("SELECT * FROM ").append("b_category t where 1=1 ")
			.append(" AND (t.type = 2 or t.type= 3 )");
		if(log.isDebugEnabled()){
			log.debug(sql);
		}
		List<BCategory> list = dao.query(BCategory.class, sql.toString());
		return list.toArray(new BCategory[]{});
	}
	
	public BTagRelation[] getTagRelat(long rid,long cid,long pid)throws Exception{
		StringBuilder sql = new StringBuilder(1024);
		sql.append("SELECT * FROM ").append("b_tag_relation t where 1=1 ");
		if(rid>0){
			sql.append(" and t.rid =").append(rid);
		}
		if(cid>0){
			sql.append(" and t.cid =").append(cid);
		}
		if(pid>0){
			sql.append(" and t.pid =").append(pid);
		}
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List<BTagRelation> list = dao.query(BTagRelation.class, sql.toString());
		return list.toArray(new BTagRelation[]{});
	}
	
	public BArticle[] getArticleById(long artId)throws Exception{
		StringBuilder sql = new StringBuilder(1024);
		sql.append("SELECT a.*,b.name FROM b_article a LEFT JOIN b_author b on a.uid=b.id")
			.append(" WHERE 1=1 ");
		if(artId>0){
			sql.append(" AND a.pid = ").append(artId);
		}
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List<BArticle> list = dao.query(BArticle.class, sql.toString());
		return list.toArray(new BArticle[]{});
	}
	
	public BArticle[] getArticleTopNew()throws Exception{
		StringBuilder sql = new StringBuilder(1024);
		sql.append("SELECT t.* FROM b_article t ORDER BY t.pid DESC LIMIT 0,5");
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List<BArticle> list = dao.query(BArticle.class, sql.toString());
		return list.toArray(new BArticle[]{});
	}
	
	public BArticle[] getArticleRandom()throws Exception{
		StringBuilder sql = new StringBuilder(1024);
		sql.append("SELECT sh1.* FROM b_article AS sh1 ")
			.append(" JOIN ")
			.append(" (SELECT ")
			.append(" ROUND( ")
			.append("	RAND() * ((SELECT MAX(pid) FROM b_article)-(SELECT MIN(pid) FROM b_article)) + (SELECT MIN(pid) FROM b_article) ")
			.append("	) AS pid ")
			.append(" ) AS sh2 ")
			.append(" WHERE sh1.pid>=sh2.pid ")
			.append(" ORDER BY sh1.pid LIMIT 5 ");
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List<BArticle> list = dao.query(BArticle.class, sql.toString());
		return list.toArray(new BArticle[]{});
	}
	
	public long getArticleCount()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) count FROM b_article WHERE 1 =1 ");
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		return dao.getCount(sql.toString());
	}
	
	public long saveArticle(BArticle article)throws Exception{
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		dao.insert(article);
		return 0L;
	}
	
	public BArticle[] qryArticle(long artid, long userId, long categoryid,String title){
		StringBuilder sql = new StringBuilder(1024);
		return null;
	}
	
	public BArticle[] qryArticleByTag(String tagName,long tagId){
		return null;
	}
	
	public BArticle[] qryArticleByCategory(long categoryId){
		
		return null;
	}
	
	private <T>T[] qryByCond(Class<T> clazz, String sql ,Map condtion){
		return null;
	}
	
	private String buildPrepSql(String table,Map cond){
		StringBuilder sb = new StringBuilder(1024);
		
		return sb.toString();
	}
	
	public void save(Object[] objs)throws Exception{
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		dao.insert(objs);
	}
}
