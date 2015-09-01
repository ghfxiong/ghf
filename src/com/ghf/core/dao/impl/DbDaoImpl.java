package com.ghf.core.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import com.ghf.core.dao.connections.DbConnUtil;
import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.core.dao.utils.DbUtils;

public class DbDaoImpl<T> implements DbDao<T>{
	private static Logger log = Logger.getLogger(DbDaoImpl.class);
	private DbConnUtil dbConn;
	
	private DbConnUtil getConn(String className)throws Exception{
		if(dbConn==null){
			dbConn = (DbConnUtil)Class.forName(className).newInstance();
		}
		return dbConn;
	}
	private DbConnUtil getConn()throws Exception{
		return getConn(DbUtils.jdbc);
		//return getConn("com.ghf.common.utils.MySqlConnUtil");
	}
	public List query(Class clazz, String sql) throws Exception {
		//TODO 防止sql注入
		/*ReflectHelper help = new ReflectHelper();
		log.debug("sql is ----- "+sql);
		ResultSet res = getConn().query(sql);
		List list = help.getList(clazz, res);
		if(res!=null){
			res.close();
		}*/
		List list = getConn().query(clazz,sql);
		return list;
	}
	
	public void insert(Object obj) throws Exception{
		//TODO 防止sql注入
		//TestDao test = new TestDao();
		getConn().executeSql(createSql(obj));
	}
	
	public void insert(Object[] objs)throws Exception{
		List list = new ArrayList();
		if(objs!=null&&objs.length>0){
			/*String[] sqls =new String[objs.length];
			for(int i=0;i<objs.length;i++){
				sqls[i]=createSql(objs[i]);
			}
			getConn().executeSql(sqls);*/
			for(Object obj : objs){
				list.add(obj);
			}
			getConn().batchInsert(list);
		}
	}
	
	public void insert(Object[] objs,boolean flag)throws Exception{
		if(objs!=null&&objs.length>0){
			String[] sqls =new String[objs.length];
			for(int i=0;i<objs.length;i++){
				sqls[i]=createSql(objs[i]);
			}
			getConn().executeSql(sqls,flag);
		}
	}
	
	public static String createSql(Object obj)throws Exception{
		Class clazz = obj.getClass();
		Field f1= clazz.getDeclaredField("tableName");
		f1.setAccessible(true);
		StringBuilder sts = new StringBuilder("insert into ")
			.append(f1.get(clazz))
			.append(" (");
		StringBuilder sf = new StringBuilder();
		StringBuilder values = new StringBuilder();
		Field[] fields = clazz.getFields();//获得属性
		Map map = new HashMap();
		for(Field field:fields){
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
			Method getMethod = pd.getReadMethod();//获得get方法
			Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			if(o!=null){
				//sf.append("`").append(field.getName()).append("`").append(",");
				sf.append(field.getName()).append(",");
				values.append("'").append(o).append("'").append(",");
				//map.put(field.getName(), o);
			}
		}
		sts.append(sf.toString().substring(0, sf.toString().length()-1))
		   .append(") values (")
		   .append(values.toString().substring(0, values.toString().length()-1))
		   .append(");");
		
		return sts.toString();
	}
	
	
	
	public void update(Object obj) throws Exception{
		//TODO 
		Class clazz = obj.getClass();
		Field f1= clazz.getDeclaredField("tableName");
		f1.setAccessible(true);
		StringBuffer sts = new StringBuffer("update ")
			.append(f1.get(clazz))
			.append(" (");
		StringBuffer sf = new StringBuffer();
		StringBuffer values = new StringBuffer();
		Field[] fields = clazz.getFields();//获得属性
		Map map = new HashMap();
		for(Field field:fields){
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
			Method getMethod = pd.getReadMethod();//获得get方法
			Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			if(o!=null&&!"".equals(o)){
				sf.append(field.getName()).append(",");
				values.append("'").append(o).append("'").append(",");
				//map.put(field.getName(), o);
			}
		}
		sts.append(sf.toString().substring(0, sf.toString().length()-1))
		   .append(") values (")
		   .append(values.toString().substring(0, values.toString().length()-1))
		   .append(")");
		log.debug(sts.toString());
		//test.executeSql(sts.toString());
	}
	public long getCount(String sql) throws Exception {
		/*StringBuffer sb = new StringBuffer("SELECT COUNT(*) count FROM ( ");
		sb.append(" ").append(sql).append("  ) tt");
		ResultSet res = getConn().query(sb.toString());*/
		/*ResultSet res = getConn().query(sql);
		res.next();
		long count = res.getLong("count");
		res.close();*/
		return getConn().getCount(sql);
	}
}
