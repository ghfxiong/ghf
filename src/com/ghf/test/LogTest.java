package com.ghf.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	private static Logger log = Logger.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(LogTest.class.getClassLoader().getResource("log4j.properties"));
		log.debug("sdfsdfs");

	}

}
