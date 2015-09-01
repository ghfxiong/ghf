package com.ghf.utils.common;

import org.apache.commons.lang.StringUtils;

public class ServiceFactory {
	private static ServiceFactory instance = new ServiceFactory();

	public static ServiceFactory getInstance() {
		return instance;
	}	
	
	public Object getService(Class clazz)throws Exception{
		String interfaceName = clazz.getName();
		return getImplClassByInterClass(clazz).newInstance();
		//return clazz.newInstance();
	}
	
	public Class getImplClassByInterClass(Class interCls)throws Exception{
		String packageName = StringUtils.replace(interCls.getName(), "interfaces", "impl");
		String implName = packageName+"Impl";
		return Class.forName(implName);
	}
}
