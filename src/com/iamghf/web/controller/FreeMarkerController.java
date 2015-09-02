package com.iamghf.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.cache.InterCfgCache;
import com.iamghf.web.beans.blog.BArticleView;
import com.iamghf.web.beans.blog.BCategory;
import com.iamghf.web.beans.blog.BTag;
import com.iamghf.web.beans.blog.BlogInitBean;
import com.iamghf.web.service.impl.BlogServiceImpl;
import com.iamghf.web.service.interfaces.IBlogService;

/**
 * @author ghf
 *
 */
@Controller
@RequestMapping("/cpage")
public class FreeMarkerController {
	private static transient Logger log = Logger.getLogger(FreeMarkerController.class);
	@RequestMapping("/index")  
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String id = request.getParameter("id");
		String code = request.getParameter("method");
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getInterCfg(code);
		
		if(null==bean){
			//jump to 404!
		}
		
		IBlogService bsv = new BlogServiceImpl();
		BArticleView art = bsv.getArticleById(Long.parseLong(id))[0];
        /*User user = new User();  
        user.setUserName("zhangsan");  
        user.setPassWord("1234");  
        List<User> users = new ArrayList<User>();  
        users.add(user);*/
        //Map uu = new HashMap();
        //uu.put("uName", "fffff");
        //uu.put("pwd", "123456");
        //return new ModelAndView("blogContent","list",uu);
        //return new ModelAndView("blogContent", "users", users);  
		return new ModelAndView("blogContent","artv",art);
    }  
	
	@RequestMapping("/right")  
    public ModelAndView right(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String id = request.getParameter("id");
		String code = request.getParameter("method");
		String basePath = request.getContextPath();
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getInterCfg(code);
		if(null==bean){
			//jump to 404!
		}		
		BlogInitBean data = new BlogInitBean();
		BArticleView[] arts = new BArticleView[0];
		data.setArticles(arts);
		/*BaseAction ba = new BaseAction();
		String json = ba.initArticle(request, response);
		JSONObject obj = JSONObject.fromString(json);
		JSONArray ary = obj.getJSONArray("article");
		if(ary!=null && ary.length()>0){
			BArticleView[] arts = new BArticleView[ary.length()];
			for(int i=0,len=ary.length();i<len;i++){
				JSONObject tobj = ary.getJSONObject(i);
				arts[i] = new BArticleView();
				//arts[i] = (BArticleView) JSONObject.toBean(tobj, BArticleView.class);
				//arts[i] = (BArticleView) JSONUtils.convertMap(arts[i], JSONUtils.toHashMap(tobj));
				arts[i].setPid(Long.parseLong(tobj.get("id")+""));
				arts[i].setThumb(tobj.get("thumb")+"");
				
				arts[i].setUrl(tobj.get("url")+"");
				arts[i].setTitle(tobj.get("title")+"");
				arts[i].setScont(tobj.get("scont")+"");
				arts[i].setTime(tobj.get("time")+"");
				arts[i].setArtname(tobj.get("aName")+"");
				arts[i].setArttype(Integer.parseInt(tobj.get("type")+""));
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
			data.setArticles(arts);
		}*/
		
		/** 获取标签 */
		IBlogService bsv = new BlogServiceImpl();
		BCategory[] categorys = bsv.getTags();
		if(!ArrayUtils.isEmpty(categorys)){
			BTag[] tags = new BTag[categorys.length];
			for(int i=0;i< categorys.length; i++){
				BTag tag = new BTag();
				tag.setId(categorys[i].getId());
				tag.setUrl(basePath+"/jump?method=p_10001&tagid="+categorys[i].getId());
				tag.setName(categorys[i].getName());
				tags[i] = tag;
			}
			data.setTags(tags);
		}
		
		StringBuilder jsb = new StringBuilder();
		/*jsb.append("{\"data\":{ " )
		.append("\"articles\": ").append(obj.getJSONArray("article").toString())
		.append(", \"tags\": ").append(tags.toString())
		.append("}}");*/
		
		//BArticleView art = bsv.getArticleById(Long.parseLong(id))[0];
		return new ModelAndView("bRight","data",data);
    }
	
	@RequestMapping("/header")  
    public ModelAndView header(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String id = request.getParameter("id");
		String code = request.getParameter("method");
		log.debug("method:"+code);
		InterCfgBean bean = InterCfgCache.getInterCfg(code);
		
		if(null==bean){
			//jump to 404!
		}
		IBlogService bsv = new BlogServiceImpl();
		//BArticleView art = bsv.getArticleById(Long.parseLong(id))[0]; 
		return new ModelAndView("bHead","artv",null);
    }  
}
