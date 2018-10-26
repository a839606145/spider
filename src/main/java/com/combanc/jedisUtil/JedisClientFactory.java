package com.combanc.jedisUtil;

public interface JedisClientFactory {
	JedisInterface createJedis(String hostAndPort);
}
