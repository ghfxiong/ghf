package com.ghf.core.control;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ghf.core.bean.CfgControlBean;
import com.ghf.core.cache.CfgControlCache;

public  class ActionFactory {
private static ActionFactory af = null;
	private static final Logger log = Logger.getLogger(ActionFactory.class);
	private ActionFactory(){
		
	}
	private static class Factory{
		private static ActionFactory instance = new ActionFactory();
	}
	public static ActionFactory getInstance(){
		return Factory.instance;
	}
	
	public IBaseAction getAction(String type , String code)throws Exception{
		CfgControlBean cfg = CfgControlCache.getCfgCtrl(type, code);
		if(cfg!=null && StringUtils.isNotBlank(cfg.getImplService())){
			return getAction(cfg.getImplService());
		}else{
			//TODO 
			return null;
		}
	}
	
	public IBaseAction getAction(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return( IBaseAction )Class.forName(className).newInstance();
	}
	
}
