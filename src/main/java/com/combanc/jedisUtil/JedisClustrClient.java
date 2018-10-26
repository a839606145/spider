package com.combanc.jedisUtil;

import java.util.Set;

import redis.clients.jedis.JedisCluster;

public class JedisClustrClient implements JedisInterface{
	
	private JedisCluster jedisCluster;
	
	public JedisClustrClient(JedisCluster jedisCluster){
		this.jedisCluster=jedisCluster;
	}

	public Long setAdd(String key, String value) {
		return jedisCluster.sadd(key, value);
	}

	public Long setRem(String key, String value) {
		return jedisCluster.srem(key, value);
	}

	public Long setRems(String key, String... value) {
		return jedisCluster.srem(key, value);
	}

	public Set<String> setRembers(String key) {
		Set<String> result=jedisCluster.smembers(key);
		return result;
	}


}
