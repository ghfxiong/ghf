package com.ghf.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigLoader {
	private static Logger log = Logger.getLogger(ConfigLoader.class);
	
	public static void init(){
		log.info("开始加载配置...");
		Properties prop = new Properties();
		try {
			prop.load(ConfigLoader.class.getClassLoader().getResourceAsStream("log4j.properties"));
		} catch (IOException e) {
			log.error(e);
		}
		log.info("配置加载完毕！");
	}
	
	public static void loadCfg(String fileName) throws IOException{
		Properties prop = new Properties(); 
		prop.load(ConfigLoader.class.getClassLoader().getResourceAsStream(fileName));
	}
}
