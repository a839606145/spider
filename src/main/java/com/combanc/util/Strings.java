package com.combanc.util;

public class Strings {

	public static boolean isNullOrEmpty(String s){
		if(s==null||"".equals(s)){
			return true;
		}
		return false;
	}
}