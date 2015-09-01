package com.ghf.core.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

public class DbProxy implements InvocationHandler{
	private static Logger log = Logger.getLogger(DbProxy.class);
	
	private Object obj;
	
	public DbProxy(Object obj){
		this.obj=obj;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		doBefore();
		Object param = method.invoke(obj, args);
		doAfter();
		return param;
	}
	public void doBefore(){
		log.debug("do before business logic");
	}
	public void doAfter(){
		log.debug("do after business logic");
	}
	public static Object factory(Object obj){
		Class cls = obj.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),new DbProxy(obj));
	}
}
