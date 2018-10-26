package com.combanc.jedisUtil;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClient  implements JedisInterface{
	
	private JedisPool jedisPool;
	public JedisClient(JedisPool jedisPool){
		this.jedisPool=jedisPool;
	}
	
	private JedisClient(){
	}

	public Long setAdd(String key, String value) {
		Jedis redis=jedisPool.getResource();
		Long result=redis.sadd(key, value);
		redis.close();
		return result;
	}

	public Long setRem(String key, String value) {
		Jedis redis=jedisPool.getResource();
		Long result=redis.srem(key, value);
		redis.close();
		return result;
	}

	public Long setRems(String key, String... value) {
		Jedis redis=jedisPool.getResource();
		Long result=redis.srem(key, value);
		redis.close();
		return result;
	}

	public Set<String> setRembers(String key) {
		Jedis redis=jedisPool.getResource();
		Set<String> result=redis.smembers(key);
		redis.close();
		return result;
	}

	public static JedisInterface createJedis(String hostAndPort) {
		return null;
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}


}
