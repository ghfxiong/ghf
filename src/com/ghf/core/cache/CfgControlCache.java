package com.ghf.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ghf.core.bean.CfgControlBean;
/**
 * ∆Ù∂Ø ±º”‘ÿ
 * @author ghf
 *
 */
public class CfgControlCache {
	private static final Logger log = Logger.getLogger(CfgControlCache.class);
	private static Map<String,CfgControlBean> datas = new HashMap<String,CfgControlBean>();
	
	static{
		init();
	}
	
	private static void init(){
		
	}
	
	public static Map<String,CfgControlBean> getAllData(){
		if(datas.size() == 0) {
			init();
		}
		return datas;
	}
	
	public static CfgControlBean getCfgCtrl(String type,String code){
		//TODO
		return null;
	}
}
