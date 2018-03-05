package com.fisglobal.emtech.utils;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Singleton Config file that reads the config.properties file from the
 * classpath.
 * 
 * @author e0079140
 *
 */
public class Config {

	final static Logger logger = Logger.getLogger(Config.class);

	private static Config instance = null;
	public static String driverName = null;
	public static String jdbc = null;
	public static String ip = null;
	public static String port = null;
	public static String database = null;
	public static String user = null;
	public static String pass = null;

	public static Config getInstance() throws FISException {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	protected Config() throws FISException {
		try {

			Properties props = new Properties();
			props.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));

			driverName = props.getProperty("drivername");
			jdbc = props.getProperty("jdbc");
			ip = props.getProperty("ip");
			port = props.getProperty("port");
			database = props.getProperty("database");
			user = props.getProperty("user");
			pass = props.getProperty("pass");
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new FISException(ex);
			// throw new UnsupportedOperationException();
		}
	}
}
