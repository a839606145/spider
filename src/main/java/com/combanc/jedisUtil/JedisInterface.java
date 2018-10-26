package com.combanc.jedisUtil;

import java.util.Set;

public interface JedisInterface {
	
	public abstract Long setAdd(String key,String value);
	
	public abstract Long setRem(String key,String value);
	
	public abstract Long setRems(String key,String...value);
	
	public abstract Set<String> setRembers(String key);

}
