package com.ghf.core.dao.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ghf.core.dao.utils.DbUtils;
import com.ghf.core.dao.utils.ReflectHelper;

public class MySqlConnUtil<T> implements DbConnUtil<T> {
	private static Logger log = Logger.getLogger(MySqlConnUtil.class);
	
	private static String driver = "com.mysql.jdbc.Driver";
	
	//loc
	/*private static String url = "jdbc:mysql://127.0.0.1:3306/test";
	private static String user = "root";
	private static String password = "123456";*/
	
	//jae
	private static String url = "jdbc:mysql://10.0.16.16:4066/iamghf_mysql_kmyefjfc";
	private static String user = "UK3wuFgF";
	private static String password = "c07bfIDWib6e";
	
	//sae
	/*private static String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_iamghf";
	private static String url2="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_iamghf";
	//String sql = "SELECT * FROM app_table";
	// 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key
	private static String user="4jkllml13w";
	private static String password="z4km04k503hhy5h1i5jh4lxlzml2lmhl31lzl31w";*/
	//private static String user = com.sina.sae.util.SaeUserInfo.getAccessKey();
	//private static String password = com.sina.sae.util.SaeUserInfo.getSecretKey();
	//private static String Driver="com.mysql.jdbc.Driver";
	
	private static int leng = 100;
	private Statement stmt = null;
	private PreparedStatement ptmt = null;
	private Connection conn = null;
	private Connection conn2 = null;
	
	static {
		try {
            //Class.forName(driver).newInstance();
			Class.forName(DbUtils.driver).newInstance();
        } catch (Exception e) {
            log.debug("无法找到驱动器");
            e.printStackTrace();
        }
	}
	private  Connection create(){
		// 创建连接
        try {
        	//conn = DriverManager.getConnection(url, user, password);
			//conn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.password);
        	conn = JdbcPoolProxy.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	private  Connection create2(){
		// 创建连接
        try {
        	//conn2 = DriverManager.getConnection(url2, user, password);
			//conn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn2;
	}
	private void close(){
		if(stmt!=null){
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(ptmt!=null){
			try{
				ptmt.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void executeSql(String sql){
		try{
			create();
			stmt = conn.createStatement();
            // 执行SQL 插入语句
            stmt.executeUpdate(sql);
            close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void executeSql(String[] sqls){
		long start = System.currentTimeMillis();
		log.debug(" lev1 start time :"+start+"   total sqls :"+sqls.length);
		try{
			create();
			if(sqls!=null&&sqls.length>0){
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				//String pre_sql = createPreSql(sqls);
				//ptmt = conn.prepareStatement(pre_sql);
				//log.debug(pre_sql);
				for(String sql:sqls){
					log.debug(sql);
					stmt.addBatch(sql);
				}
				stmt.executeBatch();
				conn.commit();
				long end = System.currentTimeMillis();
				log.debug("total time :" + (end-start));
				close();
			}
			
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	public void executeSql(String[] sqls,boolean flag){
		long start = System.currentTimeMillis();
		log.debug(" lev1 start time :"+start+"   total sqls :"+sqls.length);
		try{
			create();
			if(sqls!=null&&sqls.length>0){
				conn.setAutoCommit(false);
				//stmt = conn.createStatement();
				String pre_sql = createPreSql(sqls);
				ptmt = conn.prepareStatement(pre_sql);
				log.debug(pre_sql);
				/*for(String sql:sqls){
					log.debug(sql);
					stmt.addBatch(sql);
				}*/
				int val_len = getValueLen(sqls);
				int len = sqls.length; //21
				int times = len/leng; //2
				int rem = len%leng; //1
				for(int i=0;i<times;i++){
					long start2 = System.currentTimeMillis();
					log.debug(" lev2 start time :"+start2);
					int testcount =0;
					for(int j=0;j<leng;j++){
						String[] vals = getValues(sqls[i*leng+j]);
						//log.debug(vals.toString());
						for(int k=0 ;k<val_len;k++){
							//ptmt.setString(k+1,vals[k]);
							ptmt.setString(k+1,vals[k]);
						}
						ptmt.addBatch();
						testcount++;
					}
					ptmt.executeBatch();
					conn.commit();
					ptmt.clearBatch();
					long end2 = System.currentTimeMillis();
					log.debug("total2 time :" + (end2-start2) + "  total sqls :" + testcount);
				}
				if(rem>0){
					for(int m =0;m<rem;m++){
						String[] vals = getValues(sqls[times*leng+m]);
						for(int k=0 ;k<val_len;k++){
							//ptmt.setString(k+1,vals[k]);
							ptmt.setString(k+1,vals[k]);
						}
						ptmt.addBatch();
					}
					ptmt.executeBatch();
					conn.commit();
					ptmt.clearBatch();
				}
				//stmt.executeBatch();
				//conn.commit();
				long end = System.currentTimeMillis();
				log.debug("total time :" + (end-start));
				close();
			}
			
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	public String[] getValues(String sql){
		String strs = sql.split("values")[0];
		String[] tmp = strs.split(",");
		String value = sql.split("values")[1];
		value = value.replace("(", "");
		value = value.replace(")", "");
		value = value.replace(";", "");
		value = value.replace("'", "");
		value = value.replace("'", "");
		return value.split(",");  //注意末尾为空情况
	}
	
	public int getValueLen(String[] sqls){
		String strs = sqls[0].split("values")[0];
		String[] tmp = strs.split(",");
		return tmp.length;
	}
	
	public String createPreSql(String[] sqls){
		String strs = sqls[0].split("values")[0];
		String[] tmp = strs.split(",");
		String pre_sql = strs+" values (";
		for(int i=0;i<tmp.length;i++){
			pre_sql +="?,";
		}
		pre_sql = pre_sql.substring(0,pre_sql.length()-1);
		pre_sql +=")";
		return pre_sql;
	}
	//废弃 有bug 连接不会释放！！！
	public ResultSet query(String sql) {
        // 创建一个数据集 用于获取查询到的行数据
        ResultSet rs = null;
        try {
            // 创建连接
        	//create2();
        	create();
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库
            //stmt = conn2.createStatement();
        	stmt = conn.createStatement();
            // 执行查询语句   获取ResultSet对象
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        //返回结果集
        return rs;
    }
	
	public List query(Class clazz, String sql) {
        // 创建一个数据集 用于获取查询到的行数据
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            // 创建连接
        	//create2();
        	create();
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库
            //stmt = conn2.createStatement();
        	stmt = conn.createStatement();
            // 执行查询语句   获取ResultSet对象
            rs = stmt.executeQuery(sql);
            ReflectHelper help = new ReflectHelper();
            list = help.getList(clazz, rs);
            rs.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }finally{
        	if(rs!=null){
        		try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	close();
        }
        //返回结果集
        return list;
    }
	
	public long getCount(String sql) {
		ResultSet rs = null;
		long count = 0L;
		try {
            // 创建连接
        	//create2();
        	create();
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库
            //stmt = conn2.createStatement();
        	stmt = conn.createStatement();
            // 执行查询语句   获取ResultSet对象
            rs = stmt.executeQuery(sql);
            rs.next();
            count = rs.getLong("count");
            rs.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }finally{
        	if(rs!=null){
        		try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	close();
        }
		return count;
	}
	
	public static void main(String[] args)throws Exception{
		//Class.forName(DbUtils.driver);
        //Connection conn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.password);
		for(int i= 5; i>0;i--){
			Connection conn = JdbcPoolProxy.getInstance().getConnection();
			Statement stat = conn.createStatement();
	        ResultSet rs = stat.executeQuery("select * from intercfg;");
	        while(rs.next()){
	        	//log.debug("name = " + rs.getString("name"));
	           // log.debug("job = " + rs.getString("occupation"));
	            log.debug("*********");
	        }
	        
	        conn.close();
	        rs.close();
		}
	}
	
	public void batchInsert(List<T> list) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
