package com.ghf.core.dao.interfaces;

import java.util.List;

public interface DbDao<T> {
	/**
	 * 
	 * @param clazz
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<T> query(Class<T> clazz, String sql)throws Exception;
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void insert(T obj)throws Exception;
	/**
	 * 
	 * @param objs
	 * @throws Exception
	 */
	public void insert(T[] objs)throws Exception;
	/**
	 * 
	 * @param objs
	 * @param flag
	 * @throws Exception
	 */
	public void insert(T[] objs,boolean flag)throws Exception;
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public long getCount(String sql)throws Exception;
}

