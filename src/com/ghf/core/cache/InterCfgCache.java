package com.ghf.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.dao.impl.DbDaoImpl;
import com.ghf.core.dao.interfaces.DbDao;

public class InterCfgCache {
	private static final Logger log = Logger.getLogger(InterCfgCache.class);
	private static Map<String,InterCfgBean> allData = new HashMap<String,InterCfgBean>();
	//private static com.sina.sae.memcached.SaeMemcache  mc = new com.sina.sae.memcached.SaeMemcache();
	static {
		//mc.init();
		init();
		log.info("load data is over!");
		log.info("总共加载-----"+allData.size()+"条数据");
	}
	
	public static void init() {
		DbDao<InterCfgBean> dao = new DbDaoImpl<InterCfgBean>();
		try {
			List<InterCfgBean> list = dao.query(InterCfgBean.class,"select * from intercfg where 1=1");
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					InterCfgBean bean = list.get(i);
					//allData.put(bean.getIntercode(), bean);
					if("1".equals(bean.getExt1())){
						allData.put(bean.getIntercode()+"$"+bean.getExt1(), bean);
					}else{
						allData.put(bean.getIntercode()+"$2", bean);
					}
					//mc.set(bean.getIntercode(), bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String,InterCfgBean> getallData() {
		if(allData.size() == 0) {
			init();
		}
		return allData;
	}
	/**
	 * 获取接口配置
	 * @param interCode
	 * @return
	 * @throws Exception
	 */
	public static InterCfgBean getInterCfg(String interCode) throws Exception {
		if(allData.size() == 0) {
			init();
		}
		InterCfgBean bean = null;
		try{
			//InterCfgBean bean = mc.get(interCode);
			bean = allData.get(interCode+"$2");
		}catch(Exception ex){
			ex.printStackTrace();
			DbDao<InterCfgBean> dao = new DbDaoImpl<InterCfgBean>();
			List<InterCfgBean> list = dao.query(InterCfgBean.class,interCode);
			return list.get(0);
		}
		return	bean;
	}
	/**
	 * 获取菜单
	 * @param interCode
	 * @return
	 */
	public static InterCfgBean getCfgPage(String interCode){
		if(allData.size() == 0) {
			init();
		}
		InterCfgBean bean = allData.get(interCode+"$1");
		
		return	bean;
	}
	
	public static InterCfgBean getCfg(String interCode)throws Exception{
		DbDao<InterCfgBean> dao = new DbDaoImpl<InterCfgBean>();
		List<InterCfgBean> list = dao.query(InterCfgBean.class,interCode);
		return list.get(0);
	}

}
