package com.ghf.core.dao.connections;

import java.util.List;

public interface DbConnUtil<T> {
	public void executeSql(String sql);
	public void executeSql(String[] sqls);
	public void executeSql(String[] sqls,boolean flag);
	public String[] getValues(String sql);
	public String createPreSql(String[] sqls);
	//���� ��bug ���Ӳ����ͷţ�����
	//public ResultSet query(String sql);
	public List<T> query(Class<T> clazz,String sql);
	public long getCount(String sql);
	
	public void batchInsert(final List<T> list)throws Exception;
}
