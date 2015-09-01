package com.ghf.core.dao.connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public final class DbConnectionPool {
	private static Logger log = Logger.getLogger(DbConnectionPool.class);
	
	private static DataSource DS;
	private static DbConnectionPool pool;
	
	public static synchronized DbConnectionPool getInstance(){
		if(null == pool){
			pool = new DbConnectionPool("jdbc:mysql://localhost:3306/test");
		}
		return pool;
	}
	
	public Connection getConn(){
		try{
			return DS.getConnection();
		}catch(Exception ex){
			log.debug("get conn error!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public DbConnectionPool(){
		
	}
	public DbConnectionPool(String connectURI){
		initDS(connectURI); 
	}
	public DbConnectionPool(String connectURI, String username, String pswd, String driverClass, int initialSize,  
            int maxActive, int maxIdle, int maxWait){
		initDS(connectURI, username, pswd, driverClass, initialSize, maxActive, maxIdle, maxWait);
	}
	public static void initDS(String connectURI){
		initDS(connectURI, "root", "123456", "com.mysql.jdbc.Driver", 5, 100, 30, 10000);  
	}
	/**
	 * 
	 * @param connectURI 数据库 
     * @param username 用户名 
     * @param pswd 密码 
     * @param driverClass 数据库连接驱动名 
     * @param initialSize 初始连接池连接个数 
     * @param maxActive 最大激活连接数 
     * @param maxIdle 最大闲置连接数 
     * @param maxWait 获得连接的最大等待毫秒数
	 */
	public static void initDS(String connectURI, String username, String pswd, String driverClass, int initialSize,  
            int maxActive, int maxIdle, int maxWait){
		BasicDataSource ds = new BasicDataSource();  
        ds.setDriverClassName(driverClass);  
        ds.setUsername(username);  
        ds.setPassword(pswd);  
        ds.setUrl(connectURI);  
        ds.setInitialSize(initialSize); // 初始的连接数；  
        ds.setMaxActive(maxActive);  
        ds.setMaxIdle(maxIdle);  
        ds.setMaxWait(maxWait);  
        DS = ds;  
	}
	
	 /** 获得数据源连接状态 */  
    public static Map<String, Integer> getDataSourceStats() throws SQLException {  
        BasicDataSource bds = (BasicDataSource) DS;  
        Map<String, Integer> map = new HashMap<String, Integer>(2);  
        map.put("active_number", bds.getNumActive());  
        map.put("idle_number", bds.getNumIdle());  
        return map;  
    }  
  
    /** 关闭数据源 */  
    protected static void shutdownDataSource() throws SQLException {  
        BasicDataSource bds = (BasicDataSource) DS;  
        bds.close();  
    } 
    
    public static void main(String[] args) {  
    	//DbConnectionPool db = new DbConnectionPool("jdbc:mysql://localhost:3306/test");  
    	DbConnectionPool db = DbConnectionPool.getInstance();
        Connection conn = null;  
        Statement stmt = null;  
        ResultSet rs = null;  
  
        try {  
            conn = db.getConn();  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery("select * from intercfg limit 1 ");  
            log.debug("Results:");  
            int numcols = rs.getMetaData().getColumnCount();  
            while (rs.next()) {  
                for (int i = 1; i <= numcols; i++) {  
                    System.out.print("\t" + rs.getString(i) + "\t");  
                }  
                log.debug("");  
            }  
            log.debug(getDataSourceStats());  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (rs != null)  
                    rs.close();  
                if (stmt != null)  
                    stmt.close();  
                if (conn != null)  
                    conn.close();  
                if (db != null)  
                    shutdownDataSource();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
