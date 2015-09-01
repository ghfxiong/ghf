package com.ghf.core.lucene;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LuceneHandle implements InvocationHandler{
	private Object obj;
	
	public void register(Object object){
		this.obj = object;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		return method.invoke(this.obj, args);
	}

}
