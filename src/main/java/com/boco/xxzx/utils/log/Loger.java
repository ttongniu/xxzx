package com.boco.xxzx.utils.log;

import org.apache.log4j.Logger;

/**
 * set the log4j
 * 
 * @see org.apache.log4j.Logger;
 *  
 * @author Metanoia.Lang
 * 
 */

public class Loger {

	public static Logger log(Class<?> x) {
		try {
			Logger logger = Logger.getLogger(x);
			return logger;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
