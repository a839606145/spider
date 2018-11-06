package com.combanc.util;

public class Strings {

	public static boolean isNullOrEmpty(String s){
		if(s==null||"".equals(s)){
			return true;
		}
		return false;
	}
	
	public static  String arrayToString(double[] obj){
		StringBuffer sb=new StringBuffer();
		for(double o:obj){
			sb.append(o+"/");
		}
		return sb.toString();
	}
	/**
	 * 基本数据对象不行
	 * @param obj
	 * @return
	 */
	public static <T> String arrayToString(T[] obj){
		StringBuffer sb=new StringBuffer();
		for(T o:obj){
			sb.append(o+"/");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Double[] s=new Double[]{1.0,3.0};
		System.out.println(Strings.arrayToString(s));;
	}
}
