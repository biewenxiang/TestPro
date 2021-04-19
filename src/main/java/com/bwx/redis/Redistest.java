package com.bwx.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Redistest {
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.122.130",6379);
        jedis.select(0);
        jedis.get("name");
    }
    @Test
    public void testRedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.122.130",6380));
        nodes.add(new HostAndPort("note3",6381));
        nodes.add(new HostAndPort("note3",6382));
        nodes.add(new HostAndPort("note3",6383));
        nodes.add(new HostAndPort("note3",6384));
        nodes.add(new HostAndPort("note3",6385));
        nodes.add(new HostAndPort("note3",6386));
        nodes.add(new HostAndPort("note3",6387));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.get("");
        Map<String, JedisPool> clusterNodes =
                jedisCluster.getClusterNodes();
        Set<String> keys = new HashSet<>();
        for (String key : clusterNodes.keySet()) {
            JedisPool jedisPool = clusterNodes.get(key);
            Jedis jedisConn = jedisPool.getResource();
            try {
                byte[] pattern;
                keys.addAll(jedisConn.keys("*"));
            } catch (Exception e) {
            } finally {
                jedisConn.close();
            }
        }

//        System.out.println(jedisCluster.keys("*"));
        System.out.println(jedisCluster.get("name"));
        jedisCluster.set("age", "18");

//        Jedis jedis = new Jedis("192.168.122.130",6379);
//        jedis.select(0);
//        jedis.get("name");
    }
}
