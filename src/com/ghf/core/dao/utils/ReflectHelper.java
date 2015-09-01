package com.ghf.core.dao.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReflectHelper<T> {
	private static Logger log = Logger.getLogger(ReflectHelper.class);
	
	public List<T> getList(Class<T> clazz, ResultSet rs ,boolean flag) {
		Field field = null;
		List<T> lists = new ArrayList<T>();
		// 取得类里边的所有方法
		try {
			// 取得ResultSet列名
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取记录集中的列数
			int counts = rsmd.getColumnCount();
			// 定义counts个String 变量
			String[] columnNames = new String[counts];
			// 给每个变量赋值
			for (int i = 0; i < counts; i++) {
				columnNames[i] = rsmd.getColumnLabel(i + 1);
				log.debug(i+"列名: "+columnNames[i]);
			}

			// 变量ResultSet
			while (rs.next()) {
				T t = clazz.newInstance();
				// 反射, 从ResultSet绑定到JavaBean
				for (int i = 0; i < counts; i++) {
					// 根据 rs 列名 ，组装javaBean里边的其中一个set方法，object 就是数据库第一行第一列的数据了
					Object value = rs.getObject(columnNames[i]);
					if(null!=value && !"".equals(value)){
						// 这里是获取数据库字段的类型
						Class<?> dbType = value.getClass();
						log.debug("zidu:"+value+"---"+dbType);
						// 设置参数类型，此类型应该跟javaBean 里边的类型一样，而不是取数据库里边的类型
						field = clazz.getDeclaredField(columnNames[i]);
						Class<?> beanType = field.getType();
						// 如果发生从数据库获取到得类型跟javaBean类型不同，按String类型取吧
						if (beanType != dbType) {
							if("java.lang.Long".equals(dbType.getName())){
								value = rs.getLong(columnNames[i]);
							}else if("java.lang.Integer".equals(dbType.getName())){
								value = rs.getInt(columnNames[i]);
							} else{
								value = rs.getString(columnNames[i]);
							}
						}
						String setMethodName = "set"
								+ firstUpperCase(columnNames[i]);
						// 第一个参数是传进去的方法名称，第二个参数是 传进去的类型；
						log.debug("setmethod:"+setMethodName+"---"+beanType);
						Method m = t.getClass().getMethod(setMethodName, beanType);
						// 第二个参数是传给set方法数据；如果是get方法可以不写
						if("java.lang.Long".equals(dbType.getName())){
							m.invoke(t, ((Long)value).longValue());
						}else if("java.lang.Integer".equals(dbType.getName())){
							m.invoke(t, ((Integer)value).intValue());
						}else{
							m.invoke(t, value);
						}
					}					
				}
				lists.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lists;
	}
	
	public List<T> getList(Class<T> clazz, ResultSet rs) {
		Field field = null;
		List<T> lists = new ArrayList<T>();
		// 取得类里边的所有方法
		try {
			// 取得ResultSet列名
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取记录集中的列数
			int counts = rsmd.getColumnCount();
			// 定义counts个String 变量
			String[] columnNames = new String[counts];
			// 给每个变量赋值
			for (int i = 0; i < counts; i++) {
				columnNames[i] = rsmd.getColumnLabel(i + 1).toLowerCase();
				//log.debug(i+"列名: "+columnNames[i]);
			}
			// 变量ResultSet
			while (rs.next()) {
				T t = clazz.newInstance();
				// 反射, 从ResultSet绑定到JavaBean
				for (int i = 0; i < counts; i++) {
					// 根据 rs 列名 ，组装javaBean里边的其中一个set方法，object 就是数据库第一行第一列的数据了
					Object value = rs.getObject(columnNames[i]);
					if(null!=value && !"".equals(value)){
						// 这里是获取数据库字段的类型
						// 设置参数类型，此类型应该跟javaBean 里边的类型一样，而不是取数据库里边的类型
						field = clazz.getDeclaredField(columnNames[i]);
						Class<?> beanType = field.getType();
						// 如果发生从数据库获取到得类型跟javaBean类型不同，按String类型取吧
						if("long".equals(beanType.getName())){
							
						}else if("int".equals(beanType.getName())){
							value = rs.getInt(columnNames[i]);
						}else if("long".equals(beanType.getName())){
							value = rs.getLong(columnNames[i]);
						}else if("java.lang.String".equals(beanType.getName())){
							value = rs.getString(columnNames[i]);
						}else{
							value = rs.getString(columnNames[i]);
						}
						
						String setMethodName = "set"
								+ firstUpperCase(columnNames[i]);
						// 第一个参数是传进去的方法名称，第二个参数是 传进去的类型；
						//log.debug("setmethod:"+setMethodName+"---"+beanType);
						Method m = t.getClass().getMethod(setMethodName, beanType);
						// 第二个参数是传给set方法数据；如果是get方法可以不写
						m.invoke(t, value);
					}					
				}
				lists.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lists;
	}

	// 首写字母变大写
	public static String firstUpperCase(String old) {
		return old.substring(0, 1).toUpperCase() + old.substring(1);
	}
	public void insert(){
		
	}
}
