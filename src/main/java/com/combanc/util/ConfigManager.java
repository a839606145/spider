package com.combanc.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties p=new Properties();
	static{
		try{
			InputStream in=ConfigManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
			p.load(in);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return p.getProperty(key);
	}
	
	public static Integer getPropertyInteger(String key){
		  String value = getProperty(key);
	        try {
	            return Integer.valueOf(value);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return 0;
	}
	
	public static Boolean getBoolean(String key) {
        String value = getProperty(key);
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	 public static Long getLong(String key) {
	        String value = getProperty(key);
	        try {
	            return Long.valueOf(value);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return 0L;
	    }

}
