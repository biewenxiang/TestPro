package com.bwx.vm;

import com.sun.org.apache.bcel.internal.generic.DADD;
import org.apache.storm.shade.com.google.common.cache.CacheBuilder;
import org.apache.storm.shade.com.google.common.cache.CacheLoader;
import org.apache.storm.shade.com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class AndyService {
    private final LoadingCache<String, String> cache;

    public AndyService() {
        /**
         * 5秒自动过期
         */
        cache = CacheBuilder.newBuilder().expireAfterWrite(50, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            public String load(String id) throws Exception {
                System.out.println("method inovke");
                //这里执行查询数据库，等其他复杂的逻辑

                return "User:" + id;
            }
        });
        cache.put("1", "1111");

    }

    public String getAndyName(String id) throws Exception {
        return cache.get(id);
    }
}

class GuavaCacheTest {
    public static void main(String[] args) throws Exception {
        AndyService us = new AndyService();
        for (int a = 0; a < 4; a++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; ; i++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + us.getAndyName(i % 10 + ""));
                            TimeUnit.SECONDS.sleep(1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }


            });
            thread.start();

        }

    }
}
