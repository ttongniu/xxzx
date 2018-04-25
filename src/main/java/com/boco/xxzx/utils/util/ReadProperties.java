package com.boco.xxzx.utils.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.boco.xxzx.utils.log.Loger;

/**
 * Read the properties file.
 * 
 * @author Metanoia.Lang
 * 
 */

public class ReadProperties {
	Logger logger = Loger.log(ReadProperties.class);

	private Properties prop = null;

	/**
	 * construction function.
	 * 
	 * @param filename
	 *            String Relative path for package base path
	 */
	public ReadProperties(String filename) {
		InputStream is = null;
		prop = new Properties();

		logger.info("load Properties file:" + filename + "...");
		is = this.getClass().getClassLoader().getResourceAsStream(filename);

		try {
			prop.load(is);
			logger.info("init Properties Object success");
			if (is != null)
				is.close();
			logger.info("close load Properties file:" + filename + " success");
		} catch (IOException e) {
			logger.error("load Properties file:" + filename + " IO error");
			e.printStackTrace();
		}
	}

	/**
	 * get value of key from property
	 * 
	 * @param key
	 *            String property key
	 * @return String value of key
	 */
	public String GetPara(String key) {
		return (prop.getProperty(key));
	}

	
}
