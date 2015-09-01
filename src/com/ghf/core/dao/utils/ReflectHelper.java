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
		// ȡ������ߵ����з���
		try {
			// ȡ��ResultSet����
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ��¼���е�����
			int counts = rsmd.getColumnCount();
			// ����counts��String ����
			String[] columnNames = new String[counts];
			// ��ÿ��������ֵ
			for (int i = 0; i < counts; i++) {
				columnNames[i] = rsmd.getColumnLabel(i + 1);
				log.debug(i+"����: "+columnNames[i]);
			}

			// ����ResultSet
			while (rs.next()) {
				T t = clazz.newInstance();
				// ����, ��ResultSet�󶨵�JavaBean
				for (int i = 0; i < counts; i++) {
					// ���� rs ���� ����װjavaBean��ߵ�����һ��set������object �������ݿ��һ�е�һ�е�������
					Object value = rs.getObject(columnNames[i]);
					if(null!=value && !"".equals(value)){
						// �����ǻ�ȡ���ݿ��ֶε�����
						Class<?> dbType = value.getClass();
						log.debug("zidu:"+value+"---"+dbType);
						// ���ò������ͣ�������Ӧ�ø�javaBean ��ߵ�����һ����������ȡ���ݿ���ߵ�����
						field = clazz.getDeclaredField(columnNames[i]);
						Class<?> beanType = field.getType();
						// ������������ݿ��ȡ�������͸�javaBean���Ͳ�ͬ����String����ȡ��
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
						// ��һ�������Ǵ���ȥ�ķ������ƣ��ڶ��������� ����ȥ�����ͣ�
						log.debug("setmethod:"+setMethodName+"---"+beanType);
						Method m = t.getClass().getMethod(setMethodName, beanType);
						// �ڶ��������Ǵ���set�������ݣ������get�������Բ�д
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
		// ȡ������ߵ����з���
		try {
			// ȡ��ResultSet����
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ��¼���е�����
			int counts = rsmd.getColumnCount();
			// ����counts��String ����
			String[] columnNames = new String[counts];
			// ��ÿ��������ֵ
			for (int i = 0; i < counts; i++) {
				columnNames[i] = rsmd.getColumnLabel(i + 1).toLowerCase();
				//log.debug(i+"����: "+columnNames[i]);
			}
			// ����ResultSet
			while (rs.next()) {
				T t = clazz.newInstance();
				// ����, ��ResultSet�󶨵�JavaBean
				for (int i = 0; i < counts; i++) {
					// ���� rs ���� ����װjavaBean��ߵ�����һ��set������object �������ݿ��һ�е�һ�е�������
					Object value = rs.getObject(columnNames[i]);
					if(null!=value && !"".equals(value)){
						// �����ǻ�ȡ���ݿ��ֶε�����
						// ���ò������ͣ�������Ӧ�ø�javaBean ��ߵ�����һ����������ȡ���ݿ���ߵ�����
						field = clazz.getDeclaredField(columnNames[i]);
						Class<?> beanType = field.getType();
						// ������������ݿ��ȡ�������͸�javaBean���Ͳ�ͬ����String����ȡ��
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
						// ��һ�������Ǵ���ȥ�ķ������ƣ��ڶ��������� ����ȥ�����ͣ�
						//log.debug("setmethod:"+setMethodName+"---"+beanType);
						Method m = t.getClass().getMethod(setMethodName, beanType);
						// �ڶ��������Ǵ���set�������ݣ������get�������Բ�д
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

	// ��д��ĸ���д
	public static String firstUpperCase(String old) {
		return old.substring(0, 1).toUpperCase() + old.substring(1);
	}
	public void insert(){
		
	}
}
