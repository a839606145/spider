package com.combanc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {

	 public static byte[] download(String urlLocation) {
	        
	        HttpURLConnection conn = null;
	        InputStream is = null;
	        try {
	            URL url = new URL(urlLocation);
	            conn = (HttpURLConnection)url.openConnection();
	            conn.setDoInput(true);
	            conn.setDoOutput(false);
	            conn.setUseCaches(false);
	            conn.setRequestMethod("GET");
	            conn.connect();
	            
	            is = conn.getInputStream();
	            return IOUtils.toByteArray(is);
	        } catch (Exception e) {
	            throw new RuntimeException("璇锋眰閿欒锛�", e);
	        } finally {
	        	if(is!=null){
	        		try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        	}
	            if(conn != null) conn.disconnect();
	        }
	    }
}
