package com.combanc.jedisUtil;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.combanc.util.Strings;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisPoolClient implements JedisInterface {
	
	private static final Logger logger=Logger.getLogger(JedisPoolClient.class);
	
	private void init(String redisHostAndPort){
		boolean isCluster=true;
		if(isCluster){
			Set<HostAndPort> hosts = new HashSet<HostAndPort>();
            HostAndPort hostAndPort = null;
            //集群模式创建
            if(Strings.isNullOrEmpty(redisHostAndPort)){
                logger.error("redis cluster no specified machine !");
                throw new RuntimeException("redis cluster no specified machine !");
            }
            
            String[] split = redisHostAndPort.split(",");
            for(String s:split){
            	String[] host_port = s.split(":");
            	if(host_port.length==2){
            		int port;
            		try{
            			port=Integer.parseInt(host_port[1]);
            		}catch (Exception e) {
            			throw new NumberFormatException("port must to be a number");
					}
            		hostAndPort = new HostAndPort(host_port[0],port);
                    hosts.add(hostAndPort);
                    if(hosts == null || hosts.size() == 0){
                        logger.error("redis cluster no specified machine !");
                        throw new RuntimeException("redis cluster no specified machine !");
                    }
                    JedisCluster cluster = new JedisCluster(hosts);
                    jedisClent = new JedisClustrClient(cluster);
                    
            	}else{
            		throw new IllegalArgumentException("host_port is wrong set");
            	}
                
            }
           
		}else{
			 //单机模式
            if(Strings.isNullOrEmpty(redisHostAndPort)){
                logger.error("redis no specified machine !");
                throw new RuntimeException("redis no specified machine !");
            }
            String[] host_port = redisHostAndPort.split(":");
        	if(host_port.length==2){
        		int port;
        		try{
        			port=Integer.parseInt(host_port[1]);
        			JedisPool pool = new JedisPool(host_port[0],port);
        			jedisClent=new JedisClient(pool);
        		}catch (Exception e) {
        			throw new NumberFormatException("port must to be a number");
				}
		  }
		}
	}

	private JedisInterface jedisClent;
	@Override
	public Long setAdd(String key, String value) {
		return jedisClent.setAdd(key, value);
	}

	@Override
	public Long setRem(String key, String value) {
		return jedisClent.setRem(key, value);
	}

	@Override
	public Long setRems(String key, String... value) {
		return jedisClent.setRems(key, value);
	}

	@Override
	public Set<String> setRembers(String key) {
		return jedisClent.setRembers(key);
	}

	public JedisInterface getJedisClent() {
		return jedisClent;
	}

	public void setJedisClent(JedisInterface jedisClent) {
		this.jedisClent = jedisClent;
	}

}
