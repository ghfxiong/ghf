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
		
	// 创建连接池
	private static LinkedList<Connection> connections = new LinkedList<Connection>();
	
	public static JdbcPoolProxy getInstance(){
		if(null == pool){
			pool = new JdbcPoolProxy();
		}
		return pool;
	}
	
	static {
		// 获取properties的配置文件,并以流的方式存储
		InputStream inputStream = JdbcPoolProxy.class.getClassLoader()
				.getResourceAsStream("db.properties");
		// 创建properties的属性处理对象
		Properties properties = new Properties();
		try {
			// 将属性文件载入
			properties.load(inputStream);
			// 获取连接的驱动文件
			Class.forName(properties.getProperty("driver"));
			int maxConns = StringUtils.isEmpty(properties.getProperty("max"))?20:Integer.parseInt(properties.getProperty("max"));
			// 循环创建连接并放入连接池
			for (int i = 0; i < 10; i++) {
				// 创建连接对象
				final Connection conn = DriverManager.getConnection(
						properties.getProperty("url"),
						properties.getProperty("user"),
						properties.getProperty("password"));

				// 将创建分连接对象添加到连接池
				// 通过动态代理处理close的方法实现取出的连接对象返回连接池的效果
				connections.add((Connection) Proxy.newProxyInstance(
						JdbcPoolProxy.class.getClassLoader(),
						new Class[] { Connection.class },
						new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method,
									Object[] args) throws Throwable {
								// 判断当前执行的方法名是不是close时还执行自己的方法体
								if (!method.getName().equals("close")) {
									// 执行目标方法
									return method.invoke(conn, args);
								}
								// 如果是close方法
								// 向连接池中添加连接对象
								connections.add(conn);
								if(log.isDebugEnabled()){
									log.debug("又一个连接用玩完了,返回个连接池,当前连接池有"
											+ connections.size() + "个连接对象");
								}
								return null;
							}
						}));
				if(log.isDebugEnabled()){
					log.debug("线连接池添加了一个链接对象,当前连接池有======"
							+ connections.size() + "=====个连接对象");
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
		// 声明连接对象
		Connection conn = null;
		// 判断连接池中是否有连接对象
		if (connections.size() > 0) {
			// 从连接池取出连接对象
			conn = connections.removeFirst();
			log.debug("有一个连接对象被占用,连接池还有=========" + connections.size()
					+ "个连接");
		}
		return conn;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
