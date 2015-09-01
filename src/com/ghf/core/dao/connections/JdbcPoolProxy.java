package com.ghf.core.dao.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class JdbcPoolProxy<T> implements DataSource{
	private static Logger log = Logger.getLogger(JdbcPoolProxy.class);
	
	private static JdbcPoolProxy pool;
		
	// �������ӳ�
	private static LinkedList<Connection> connections = new LinkedList<Connection>();
	
	public static JdbcPoolProxy getInstance(){
		if(null == pool){
			pool = new JdbcPoolProxy();
		}
		return pool;
	}
	
	static {
		// ��ȡproperties�������ļ�,�������ķ�ʽ�洢
		InputStream inputStream = JdbcPoolProxy.class.getClassLoader()
				.getResourceAsStream("db.properties");
		// ����properties�����Դ������
		Properties properties = new Properties();
		try {
			// �������ļ�����
			properties.load(inputStream);
			// ��ȡ���ӵ������ļ�
			Class.forName(properties.getProperty("driver"));
			int maxConns = StringUtils.isEmpty(properties.getProperty("max"))?20:Integer.parseInt(properties.getProperty("max"));
			// ѭ���������Ӳ��������ӳ�
			for (int i = 0; i < 10; i++) {
				// �������Ӷ���
				final Connection conn = DriverManager.getConnection(
						properties.getProperty("url"),
						properties.getProperty("user"),
						properties.getProperty("password"));

				// �����������Ӷ�����ӵ����ӳ�
				// ͨ����̬������close�ķ���ʵ��ȡ�������Ӷ��󷵻����ӳص�Ч��
				connections.add((Connection) Proxy.newProxyInstance(
						JdbcPoolProxy.class.getClassLoader(),
						new Class[] { Connection.class },
						new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method,
									Object[] args) throws Throwable {
								// �жϵ�ǰִ�еķ������ǲ���closeʱ��ִ���Լ��ķ�����
								if (!method.getName().equals("close")) {
									// ִ��Ŀ�귽��
									return method.invoke(conn, args);
								}
								// �����close����
								// �����ӳ���������Ӷ���
								connections.add(conn);
								if(log.isDebugEnabled()){
									log.debug("��һ��������������,���ظ����ӳ�,��ǰ���ӳ���"
											+ connections.size() + "�����Ӷ���");
								}
								return null;
							}
						}));
				if(log.isDebugEnabled()){
					log.debug("�����ӳ������һ�����Ӷ���,��ǰ���ӳ���======"
							+ connections.size() + "=====�����Ӷ���");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Connection getConnection() throws SQLException {
		// �������Ӷ���
		Connection conn = null;
		// �ж����ӳ����Ƿ������Ӷ���
		if (connections.size() > 0) {
			// �����ӳ�ȡ�����Ӷ���
			conn = connections.removeFirst();
			log.debug("��һ�����Ӷ���ռ��,���ӳػ���=========" + connections.size()
					+ "������");
		}
		return conn;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
