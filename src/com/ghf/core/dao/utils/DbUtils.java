package com.ghf.core.dao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ghf.utils.ConfigLoader;

/**
 * 初始化数据库连接参数
 * 
 * @author ghf
 *
 */
public class DbUtils{
	public static String driver = null;
	public static String connName = null;
	public static String url = null;
	public static String user = null;
	public static String password = null;
	public static String jdbc = null;
	public static Object impl = null;
	/*driver=com.mysql.jdbc.Driver
	connName=jdbc:sqlite:test.db
	url=jdbc:mysql://127.0.0.1:3306/test
	user=root
	password=123456
	#jdbc=com.ghf.common.utils.SQLiteConnUtil
	jdbc=com.ghf.common.utils.MySqlConnUtil*/
	static{
		Properties prop = new Properties();
		//File file = new File("config/db.properties"); /APP
		//File file = new File("db.properties"); //WEB
		try {
			//prop.load(new FileInputStream(file));
			prop.load(DbUtils.class.getClassLoader()
				.getResourceAsStream("db.properties"));
			//ConfigLoader.loadCfg("db.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver = prop.getProperty("driver");
		connName = prop.getProperty("connName");
		user = prop.getProperty("user");
		url = prop.getProperty("url");
		password = prop.getProperty("password");
		jdbc = prop.getProperty("jdbc");
		try {
			impl = Class.forName(jdbc).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}