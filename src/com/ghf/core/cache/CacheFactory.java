package com.ghf.core.cache;

import org.apache.log4j.Logger;


public class CacheFactory<T> {
	private  T cache;
	private static final Logger log = Logger.getLogger(CacheFactory.class);
	private CacheFactory(){
		
	}
	private static class Factory{
		private static CacheFactory instance = new CacheFactory();
	}
	public static CacheFactory getInstance(){
		return Factory.instance;
	}
	
	public static  Object getCahceImpl(){
		return null;
	}
	
}
