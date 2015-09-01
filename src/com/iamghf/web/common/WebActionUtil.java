package com.iamghf.web.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ghf.bean.Page;
import com.ghf.bean.Pagination;
import com.ghf.utils.common.ComConst;


public class WebActionUtil {
	public static Map setPageMap(HttpServletRequest req, HttpServletResponse resp){
		//默认每页10条
		int pagesize =10;
		int page = 1;
		String pagesizeStr = req.getParameter("pagesize")==null ? (String)req.getAttribute("pagesize"):req.getParameter("pagesize");
        String pageStr = req.getParameter("page")==null ?(String) req.getAttribute("page"):req.getParameter("page");
        if(StringUtils.isNumeric(pagesizeStr)){
        	pagesize = Integer.parseInt(pagesizeStr);
        }
        if(StringUtils.isNumeric(pageStr)){
        	page = Integer.parseInt(pageStr);
        }
        page = page==0?1:page;
        int beginIndex = pagesize * (page-1);
        int endIndex = pagesize * page;
        Map pageMap = new HashMap();
        pageMap.put(ComConst.DB_CONST.PAGE_SIZE, pagesize);
        pageMap.put(ComConst.DB_CONST.CURRENT_PAGE, page);
        //pageMap.put(ComConst.DB_CONST.MAX_PAGE, 0);
        pageMap.put(ComConst.DB_CONST.BEGIN_INDEX, beginIndex);
        pageMap.put(ComConst.DB_CONST.END_INDEX, endIndex);
        return pageMap;
	}
	
	public static Pagination setPage(HttpServletRequest req, HttpServletResponse resp){
		//默认每页10条
		int pagesize =10;
		int page = 1;
		String pagesizeStr = req.getParameter("pagesize")==null ? (String)req.getAttribute("pagesize"):req.getParameter("pagesize");
        String pageStr = req.getParameter("page")==null ?(String) req.getAttribute("page"):req.getParameter("page");
        if(StringUtils.isNumeric(pagesizeStr)){
        	pagesize = Integer.parseInt(pagesizeStr);
        }
        if(StringUtils.isNumeric(pageStr)){
        	page = Integer.parseInt(pageStr);
        }
        page = page==0?1:page;
        int beginIndex = pagesize * (page-1);
        int endIndex = pagesize * page;
		
        Pagination pageBean = new Pagination();
		pageBean.setCurrentPage(page);
		pageBean.setEndIndex(endIndex);
		//pageBean.setMaxPage(page);
		pageBean.setPageSize(pagesize);
		pageBean.setStartIndex(beginIndex);
		return pageBean;
	}
	
	public static Map convert2Map(Pagination pageBean){
		Map pageMap = new HashMap();
        pageMap.put(ComConst.DB_CONST.PAGE_SIZE, pageBean.getPageSize());
        pageMap.put(ComConst.DB_CONST.CURRENT_PAGE, pageBean.getCurrentPage());
        pageMap.put(ComConst.DB_CONST.MAX_PAGE, pageBean.getMaxPage());
        pageMap.put(ComConst.DB_CONST.BEGIN_INDEX, pageBean.getStartIndex());
        pageMap.put(ComConst.DB_CONST.END_INDEX, pageBean.getEndIndex());
        return pageMap;
	}
	
	public static Pagination convert2Pagination(Map pageMap){
		Pagination pageBean = new Pagination();
		pageBean.setCurrentPage(formatObj2Int(pageMap.get(ComConst.DB_CONST.CURRENT_PAGE),1));
		pageBean.setEndIndex(formatObj2Long(pageMap.get(ComConst.DB_CONST.END_INDEX),0));
		pageBean.setMaxPage(formatObj2Int(pageMap.get(ComConst.DB_CONST.MAX_PAGE),1));
		pageBean.setPageSize(formatObj2Int(pageMap.get(ComConst.DB_CONST.PAGE_SIZE),10));
		pageBean.setStartIndex(formatObj2Long(pageMap.get(ComConst.DB_CONST.BEGIN_INDEX),0));
		return pageBean;
	}
	
	public static int formatObj2Int(Object obj,int def){
		if(obj==null || "".equals(obj) || !StringUtils.isNumeric(obj.toString())){
			return def;
		}else{
			return Integer.parseInt(obj.toString());
		}
	}
	
	public static long formatObj2Long(Object obj,long def){
		if(obj==null || "".equals(obj) || !StringUtils.isNumeric(obj.toString())){
			return def;
		}else{
			return Long.parseLong(obj.toString());
		}
	}
}
