package com.iamghf.web.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ghf.bean.Pagination;
import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.cache.InterCfgCache;
import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.utils.common.ComConst;
import com.ghf.utils.common.ServiceFactory;
import com.iamghf.web.beans.QKfInfo;
import com.iamghf.web.beans.blog.BArticle;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.beans.blog.BCategory;
import com.iamghf.web.beans.blog.BTag;
import com.iamghf.web.common.WebActionUtil;
import com.iamghf.web.common.WebConstant;
import com.iamghf.web.dao.TableSql;
import com.iamghf.web.service.impl.BlogServiceImpl;
import com.iamghf.web.service.interfaces.IBlogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseAction {
	private static Logger log = Logger.getLogger(BaseAction.class);
	/*public void query(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		QStudentSV sv = (QStudentSV) ServiceFactory.getInstance().getService(QStudentSV.class);
		sv.insert();
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		List peoples = dao.query(People.class, "select * from people");
		JSONArray list = new JSONArray();
		for(int i=0;i<peoples.size();i++){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", ((People)peoples.get(i)).getName());
			jsonObj.put("occupation", ((People)peoples.get(i)).getOccupation());
		}
		List peoples = new ArrayList();
		for(int i=0;i<3;i++){
			People p = new People();
			p.setName("name"+i);
			p.setOccupation("ffff"+i);
			peoples.add(p);
		}
		StringBuffer sql = new StringBuffer();
		//sql.append("SELECT * from test.imagejoke t where t.id>=(SELECT id from test.imagejoke a LIMIT 10,1) LIMIT 20");
		sql.append("select * from userinfo");
		//List jokes = dao.query(ImageJoke.class, sql.toString());
		List userinfos = dao.query(Userinfo.class, sql.toString());
		JSONArray JsonArr = JSONArray.fromObject(userinfos);
		out.write("{ \"Rows\":" + JsonArr.toString() + ", \"Total\": " + 10 + "}");
	}*/
	/*public void queryImageJoke(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		int pagesize;
		int page;
		String pagesizeStr = req.getParameter("pagesize")==null ? (String)req.getAttribute("pagesize"):req.getParameter("pagesize");
        String pageStr = req.getParameter("page")==null ?(String) req.getAttribute("page"):req.getParameter("page");
        if(pagesizeStr == null) {
        	pagesize = 0;
        } else {
        	pagesize = Integer.parseInt(pagesizeStr);
        }
        if(pageStr == null) {
        	page = 0;
        } else {
        	page = Integer.parseInt(pageStr);
        }
        int beginIndex = pagesize * (page) + 1;
        int endIndex = pagesize * page;
		PrintWriter out = resp.getWriter();
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from imagejoke t where t.id>=(SELECT id from imagejoke a LIMIT ")
		.append(beginIndex)
		.append(",1) order by t.id desc LIMIT ")
		.append(beginIndex).append(",")
		.append(pagesize);
		log.debug(sql.toString());
		List jokes = dao.query(ImageJoke.class, sql.toString());
		List cou = dao.query(ImageJoke.class, "select * from imagejoke ");
		int count = cou.size();
		JSONArray JsonArr = JSONArray.fromObject(jokes);
		out.write("{ \"Rows\":" + JsonArr.toString() + ", \"Total\": " + count + "}");
	}*/
	/*public void qryPubu(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from imagejoke t LIMIT 10,5;");
		List jokes = dao.query(ImageJoke.class, sql.toString());
		List cou = dao.query(ImageJoke.class, "select * from imagejoke ");
		int count = cou.size();
		JSONArray JsonArr = JSONArray.fromObject(jokes);
		out.write("{ \"Rows\":" + JsonArr.toString() + ", \"Total\": " + count + "}");
		//out.write("what a fuck!");
	}*/
	
	/*public void qryDbBall(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer sql = new StringBuffer();
		//sql.append("SELECT * from test.dbball_result t LIMIT 100");
		sql.append("SELECT * from dbball_result t ORDER BY id desc limit 100");
		List dbBall = dao.query(DbballResult.class, sql.toString());
		JSONArray JsonArr = JSONArray.fromObject(dbBall);
		out.write(JsonArr.toString());
	}*/
	/*public void qryDbBallResult(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		int id = Integer.valueOf(req.getParameter("id"))+1;
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer sql = new StringBuffer();
		//sql.append("SELECT * from test.dbball_result t LIMIT 100");
		sql.append("SELECT * from dbball_result t where t.id = ").append(id);
		List dbBall = dao.query(DbballResult.class, sql.toString());
		JSONArray JsonArr = JSONArray.fromObject(dbBall);
		log.debug(JsonArr.toString());
		out.write(JsonArr.toString());
	}*/
	/*public void qryDbBallTime(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer sql = new StringBuffer();
		//sql.append("SELECT * from test.dbball_result t LIMIT 100");
		sql.append("SELECT * from dbball_time t where t.loc=1");
		log.debug(sql.toString());
		List dbBall = dao.query(DbBallTime.class, sql.toString());
		JSONArray list = new JSONArray();
		for(int i=0;i<dbBall.size();i++){
			//JSONObject jsonObj = new JSONObject();
			DbBallTime dbtime = (DbBallTime) dbBall.get(i);
			//jsonObj.put(dbtime.getNum()+"", dbtime.getTimes());
			JSONArray tmp = new JSONArray();
			tmp.put(dbtime.getNum()+"号");
			tmp.put(dbtime.getTimes());
			list.put(tmp);
		}
		//JSONArray list = JSONArray.fromObject(dbBall);
		log.debug(list.toString());
		out.write(list.toString());
	}*/
	/**
	 * 内存监控
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void monitorMem(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		resp.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		Runtime run = Runtime.getRuntime();
		Process process = run.exec("tasklist /fo csv");
		//OutputStream output = process.getOutputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str ="";
		int i=0;
		JSONArray list = new JSONArray();
		while((str = read.readLine())!=null){
			//log.debug(str);
			if(i>0){
				//"映像名称","PID","会话名      ","会话#   ","内存使用 "
				String[] strs = str.split(",");
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", strs[0].trim());
				jsonObj.put("pid", strs[1].trim());
				jsonObj.put("mem", strs[4].trim().replace("K", "").replace(" ", ""));
				list.put(jsonObj);
			}
			i++;
		}
		out.write(list.toString());
	}
	
	/*public void qeruyUserInfo(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//默认每页10条
		int pagesize =10;
		int page = 1;
		String pagesizeStr = req.getParameter("pagesize")==null ? (String)req.getAttribute("pagesize"):req.getParameter("pagesize");
        String pageStr = req.getParameter("page")==null ?(String) req.getAttribute("page"):req.getParameter("page");
        String cond = req.getParameter("cond")==null ?(String) req.getAttribute("cond"):req.getParameter("cond");
        String cond1 = URLDecoder.decode(cond, "UTF-8");
        if(StringUtils.isEmpty(cond1)){
        	throw new Exception("查询条件为空！");
        }
        if(StringUtils.isNumeric(pagesizeStr)){
        	pagesize = Integer.parseInt(pagesizeStr);
        }
        if(StringUtils.isNumeric(pageStr)){
        	page = Integer.parseInt(pageStr);
        }
        page = page==0?1:page;
        int beginIndex = pagesize * (page-1);
        int endIndex = pagesize * page;
		
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer condtion = new StringBuffer();
		condtion.append(" where `name` like '%")
			.append(cond1)
			.append("%' or email like '%")
			.append(cond1)
			.append("%' or phone like '%")
			.append(cond1).append("%' ");
		
		StringBuffer sql = new StringBuffer();
		Map param = new HashMap();
		param.put(ComConst.DB_CONST.BEGIN_INDEX, beginIndex);
		param.put(ComConst.DB_CONST.PAGE_SIZE, pagesize);
		param.put(ComConst.DB_CONST.CURRENT_PAGE, page);
		
		StringBuilder subSql = new StringBuilder("SELECT * FROM DICT ")
			.append(condtion.toString())
			.append(" ORDER BY ID");
		
		//sql.append(this.buildPageSql("test.dict", subSql.toString(), param));
		sql.append(this.buildPageSql("dict", condtion.toString(), param));
		
		sql.append("SELECT TT.* FROM ( SELECT * from test.dict t")
			.append(condtion.toString())
			.append(" ) TT where TT.id>=(SELECT id from test.dict a ")
			.append(condtion.toString())
			.append(" LIMIT ")
		.append(beginIndex)
		.append(",1) order by TT.id desc LIMIT ")
		.append(beginIndex).append(",")
		.append(pagesize);
		
		log.debug(sql.toString());
		long stime,etime;
		stime = System.currentTimeMillis();
		List userinfos = dao.query(Dict.class, sql.toString());
		etime = System.currentTimeMillis();
		log.info("---分页查询耗时------"+(etime-stime));

		JSONArray JsonArr = JSONArray.fromObject(userinfos);
		
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*)count from dict t ").append(condtion.toString());
		stime = System.currentTimeMillis();
		//long count = dao.getCount(countSql.toString());
		long count =0l;
		etime = System.currentTimeMillis();
		//log.info("---查总数耗时------"+(etime-stime));
		long tmp = count/pagesize;
		long maxPage = count%pagesize==0?tmp:tmp+1;
		//maxPage = maxPage==0?1:maxPage;
		//log.debug(JsonArr);
		//out.write(JsonArr.toString());
		StringBuffer retStr = new StringBuffer(userinfos.size()*4);
		retStr.append("{ \"Rows\":" ).append(JsonArr.toString())
			.append(", \"Total\": ").append(count)
			.append(", \"CurPage\": ").append(page)
			.append(", \"MaxPage\": ").append(maxPage)
			.append("}");
		
		PrintWriter out = resp.getWriter();
		out.write(retStr.toString());
	}*/
	
	/*public void qeruyUserInfoCount(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		
        String cond = req.getParameter("cond")==null ?(String) req.getAttribute("cond"):req.getParameter("cond");
        String cond1 = URLDecoder.decode(cond, "UTF-8");
        if(StringUtils.isEmpty(cond1)){
        	throw new Exception("查询条件为空！");
        }
        
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer condtion = new StringBuffer();
		condtion.append(" where `name` like '%")
			.append(cond1)
			.append("%' or email like '%")
			.append(cond1)
			.append("%' or phone like '%")
			.append(cond1).append("%' ");
		
		long stime,etime;
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*)count from dict t ").append(condtion.toString());
		log.info(countSql.toString());
		stime = System.currentTimeMillis();
		long count = dao.getCount(countSql.toString());
		
		Pagination pageBean = WebActionUtil.setPage(req, resp); //page
		
		etime = System.currentTimeMillis();
		log.info("---查总数耗时------"+(etime-stime));
		long tmp = count/pageBean.getPageSize();
		long maxPage = count%pageBean.getPageSize()==0?tmp:tmp+1;
		//maxPage = maxPage==0?1:maxPage;
		StringBuffer retStr = new StringBuffer(1024);
		retStr.append("{ \"Rows\":" ).append("111")
			.append(", \"Total\": ").append(count)
			.append(", \"CurPage\": ").append(pageBean.getCurrentPage())
			.append(", \"MaxPage\": ").append(maxPage)
			.append("}");
		
		PrintWriter out = resp.getWriter();
		out.write(retStr.toString());
	}*/
	
	
	/*public void queryInfoIndex() throws Exception{
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		String cond1 = "6666@qq.com";
		StringBuffer condtion = new StringBuffer();
		condtion.append("SELECT id from dict t where `name` like '%")
			.append(cond1)
			.append("%' or email like '%")
			.append(cond1)
			.append("%' or phone like '%")
			.append(cond1).append("%' ")
			.append(" order by id ");
		log.info(condtion);
		List userinfos = dao.query(Dict.class, condtion.toString());
		
		LuceneUtil lucene = new LuceneUtil();
		lucene.createIndex(userinfos);
		//lucene.search(cond1);
		List users = lucene.query(cond1);
		
	}*/
	
	
	
	public String qeruykfInfo(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        String cond = req.getParameter("cond")==null ?(String) req.getAttribute("cond"):req.getParameter("cond");
        String cond1 = URLDecoder.decode(cond, "UTF-8");
        if(StringUtils.isEmpty(cond1)){
        	throw new Exception("查询条件为空！");
        }
        
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer condtion = new StringBuffer();
		//condtion.append(" where `name` like '%")
		condtion.append(" where `name` like '")
			.append(cond1)
			.append("%' ");
		
		StringBuffer sql = new StringBuffer();
		Pagination pageBean = WebActionUtil.setPage(req, resp); //page
		sql.append(TableSql.buildPageSql("kfinfo", "ID",condtion.toString(), WebActionUtil.convert2Map(pageBean)));		
		log.debug(sql.toString());
		long stime,etime;
		stime = System.currentTimeMillis();
		List userinfos = dao.query(QKfInfo.class, sql.toString());
		etime = System.currentTimeMillis();
		log.info("---分页查询耗时------"+(etime-stime));

		JSONArray JsonArr = JSONArray.fromObject(userinfos);
		
		StringBuffer retStr = new StringBuffer(userinfos.size()*4);
		retStr.append("{ \"Rows\":" ).append(JsonArr.toString())
			//.append(", \"Total\": ").append("")
			.append(", \"CurPage\": ").append(pageBean.getCurrentPage())
			//.append(", \"MaxPage\": ").append("")
			.append("}");
		
		//PrintWriter out = resp.getWriter();
		//out.write(retStr.toString());
		return retStr.toString();
	}
	
	public String qeruykfInfoCount(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		String cond = req.getParameter("cond")==null ?(String) req.getAttribute("cond"):req.getParameter("cond");
        String cond1 = URLDecoder.decode(cond, "UTF-8");
        
		DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		StringBuffer condtion = new StringBuffer();
		//condtion.append(" where `name` like '%")
		condtion.append(" where `name` like '")
			.append(cond1)
			.append("%' ");
		
		Pagination pageBean = WebActionUtil.setPage(req, resp); //page
		StringBuffer sql = new StringBuffer();
		sql.append(TableSql.buildPageSql("kfinfo","ID", condtion.toString(), WebActionUtil.convert2Map(pageBean)));		
		log.debug(sql.toString());
		long stime,etime;
		
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*) count from kfinfo t").append(condtion.toString());
		log.info("----total---"+countSql.toString());
		stime = System.currentTimeMillis();
		long count = dao.getCount(countSql.toString());
		etime = System.currentTimeMillis();
		log.info("---查总数耗时------"+(etime-stime));
		
		long tmp = count/pageBean.getPageSize();
		long maxPage = count%pageBean.getPageSize()==0?tmp:tmp+1;
		StringBuffer retStr = new StringBuffer();
		retStr.append("{ " )
			.append("\"Total\": ").append(count)
			.append(", \"CurPage\": ").append(pageBean.getCurrentPage())
			.append(", \"MaxPage\": ").append(maxPage)
			.append("}");
		
		//PrintWriter out = resp.getWriter();
		//out.write(retStr.toString());
		return retStr.toString();
	}
	
public String queryMenu(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		Map<String,InterCfgBean> map = InterCfgCache.getallData();
		JSONArray list = new JSONArray();
		String contextPaht = req.getContextPath();
		for(Map.Entry<String, InterCfgBean> entry : map.entrySet()){
			if("1".equals(entry.getValue().getExt1())){
				JSONObject obj = new JSONObject();
				obj.put("text", entry.getValue().getRemark());
				String url = entry.getValue().getImplclass();
				String code = entry.getValue().getIntercode().substring(2);
				//obj.put("url", req.getContextPath()+entry.getValue().getImplclass());
				if("plug".equals(entry.getValue().getImplfunc())){
					obj.put("url", req.getContextPath()+entry.getValue().getImplclass());
				}else{
					obj.put("url", contextPaht+"/page/"+code+".html");
				}
				list.put(obj);
			}
		}
		
		StringBuffer retStr = new StringBuffer();
		retStr.append("{ " )
			.append("\"children\": ").append(list.toString())
			//.append(", \"CurPage\": ").append(pageBean.getCurrentPage())
			//.append(", \"MaxPage\": ").append(maxPage)
			.append("}");
		
		//PrintWriter out = resp.getWriter();
		if(log.isDebugEnabled()){
			log.debug(retStr);
		}
		//out.write(retStr.toString());
		return retStr.toString();
	}

	public String initBlog(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		String basePath = req.getContextPath();
		/** 获取幻灯列表 */
		JSONArray slides = new JSONArray();
		for(int i=1;i<5;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", "slide-img-"+i);
			obj.put("url", basePath+"/jump?method=p_10001&tagid="+i);
			obj.put("picUrl", basePath+"/resoure/blog/images/a"+i+".jpg");
			obj.put("client", "标题"+i);
			obj.put("desc", "这里修改描述 这里修改描述 这里修改描述"+i);
			slides.put(obj);
		}
		
		/** 获取标签 */
		IBlogService bsv = new BlogServiceImpl();
		BCategory[] categorys = bsv.getTags();
		JSONArray tags = new JSONArray();
		if(!ArrayUtils.isEmpty(categorys)){
			for(BCategory category : categorys){
				JSONObject tag = new JSONObject();
				tag.put("id", category.getId());
				//tag.put("url", basePath+"/jump?method=p_10001&tagid="+category.getId());
				tag.put("url", basePath+"/jump?method=p_10008&tagid="+category.getId());
				tag.put("name", category.getName());
				tags.put(tag);
			}
		}
		/*for(int i=1;i<17;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", "slide-img-"+i);
			obj.put("url", basePath+"/jump?method=p_10001&tagid="+i);
			obj.put("name", "tag"+i);
			tags.put(obj);
		}*/
		StringBuilder retStr = new StringBuilder(1024);
		retStr.append("{ " )
			.append("\"slider\": ").append(slides.toString())
			.append(", \"tags\": ").append(tags.toString())
			.append("}");
		//PrintWriter out = resp.getWriter();
		//out.write(retStr.toString());
		return retStr.toString();
	}
	
	public String initArticle(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		String basePath = req.getContextPath();
		
		IBlogService bsv = new BlogServiceImpl();
		JSONArray article = new JSONArray();
		/** 获取最新文章列表-带缩略图 */
		BArticleView[] topNew = bsv.getArticleTopNew();
		for(int i=0;i<topNew.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", topNew[i].getPid());
			//obj.put("thumb", basePath+"/resoure/blog/images/0"+i+".jpg");
			String purl =( StringUtils.isNotEmpty(topNew[i].getThumb()) && topNew[i].getThumb().indexOf("http")>-1 )?topNew[i].getThumb(): basePath+topNew[i].getThumb() ;
			obj.put("thumb", purl);
			
			obj.put("url",basePath+topNew[i].getUrl());
			obj.put("title", topNew[i].getTitle());
			obj.put("scont", topNew[i].getScont());
			obj.put("time", topNew[i].getTime());
			obj.put("aName", topNew[i].getArtname());
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_NEW_THUMB);
			//JSONArray tags = new JSONArray();
			BTag[] btags = topNew[i].getBtag();
			if(!ArrayUtils.isEmpty(btags)){
				for(int j=0;j<2;j++){
					JSONObject tag = new JSONObject();
					tag.put("id", btags[j].getId());
					tag.put("name", btags[j].getName());
					tag.put("url", "");
					obj.put("tags", tag);
				}
			}
			article.put(obj);
		}
		/** 最新文章列表-简要 */
		for(int i=0;i<topNew.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", topNew[i].getPid());
			obj.put("title", topNew[i].getTitle());
			
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_NEW_SIMPLE);
			obj.put("url", basePath+topNew[i].getUrl());
			
			article.put(obj);
		}
		
		/** 按点击排行获取文章列表 */
		//BArticleView[] clicks = bsv.getArticleClick();
		BArticleView[] clicks = bsv.getArticleTopNew();
		for(int i=0;i<clicks.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", clicks[i].getPid());
			obj.put("title", clicks[i].getTitle());
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_CLICK);
			obj.put("url", basePath+clicks[i].getUrl());
			article.put(obj);
		}
		
		
		/** 推荐文章列表 */
		BArticleView[] rands = bsv.getArticleRecom();
		for(int i=0;i<rands.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", rands[i].getPid());
			obj.put("title", rands[i].getTitle());
		
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_RECOM_SIMPLE);
			obj.put("url", basePath+rands[i].getUrl());
			
			article.put(obj);
		}
		/** 推荐文章列表-带缩略图 */
		for(int i=0;i<rands.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", rands[i].getPid());
			String purl =( StringUtils.isNotEmpty(rands[i].getThumb()) && rands[i].getThumb().indexOf("http")>-1 )?rands[i].getThumb(): basePath+rands[i].getThumb();
			obj.put("thumb", purl);
			obj.put("title", rands[i].getTitle());
			
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_RECOM_THUMB);
			
			obj.put("url", basePath+rands[i].getUrl());
			
			article.put(obj);
		}
		//
		long count = bsv.getArticleCount();
		long pageNum = count%5==0?count/5:count/5+1;
		
		StringBuilder retStr = new StringBuilder(1024);
		retStr.append("{ " )
			.append("\"article\": ").append(article.toString())
			.append(", \"page\": ").append(pageNum)
			.append("}");
		//PrintWriter out = resp.getWriter();
		//out.write(retStr.toString());
		return retStr.toString();
	}
	
	public String qryArticle(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        //String cond = req.getParameter("cond")==null ?(String) req.getAttribute("cond"):req.getParameter("cond");
        //String cond1 = URLDecoder.decode(cond, "UTF-8");
		String basePath = req.getContextPath();
		
		Pagination pageBean = WebActionUtil.setPage(req, resp); //page
		Map pageMap = WebActionUtil.convert2Map(pageBean);

		IBlogService bsv = new BlogServiceImpl();
		//List articles = dao.query(BArticle.class, sql.toString());
		BArticleView[] views = bsv.getArticleNewPage(pageMap);
		JSONArray article = new JSONArray();
		for(int i=0;i<views.length;i++){
			JSONObject obj = new JSONObject();
			obj.put("id", views[i].getPid());
			//obj.put("thumb", basePath+"/resoure/blog/images/0"+i+".jpg");
			String purl =( StringUtils.isNotEmpty(views[i].getThumb()) && views[i].getThumb().indexOf("http")>-1 )?views[i].getThumb(): basePath+views[i].getThumb() ;
			obj.put("thumb", purl);
			
			obj.put("url",basePath+views[i].getUrl());
			obj.put("title", views[i].getTitle());
			obj.put("scont", views[i].getScont());
			obj.put("time", views[i].getTime());
			obj.put("aName", views[i].getArtname());
			obj.put("type", WebConstant.ARTICLE.ARTICLE_TYPE_NEW_THUMB);
			//JSONArray tags = new JSONArray();
			BTag[] btags = views[i].getBtag();
			if(!ArrayUtils.isEmpty(btags)){
				for(int j=0;j<2;j++){
					JSONObject tag = new JSONObject();
					tag.put("id", btags[j].getId());
					tag.put("name", btags[j].getName());
					tag.put("url", "");
					obj.put("tags", tag);
				}
			}
			article.put(obj);
		}
		
		StringBuilder retStr = new StringBuilder();
		retStr.append("{ \"Rows\":" ).append(article.toString())
			//.append(", \"Total\": ").append("")
			.append(", \"CurPage\": ").append(pageBean.getCurrentPage())
			//.append(", \"MaxPage\": ").append("")
			.append("}");
		
		//PrintWriter out = resp.getWriter();
		//out.write(retStr.toString());
		return retStr.toString();
	}
	
	public static void main(String[] args)throws Exception{
		/*Runtime run = Runtime.getRuntime();
		Process process = run.exec("tasklist /fo csv");
		//OutputStream output = process.getOutputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str ="";
		while((str = read.readLine())!=null){
			log.debug(str);
		}
		int mb = 1024*1024;
		
		long totalMemory = Runtime.getRuntime().totalMemory()/mb;
		log.debug(totalMemory);*/
		BaseAction act = new BaseAction();
		//act.queryInfoIndex();
	}
	
}
