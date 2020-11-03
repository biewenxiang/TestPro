package com.bwx.main;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.io.*;
import java.util.*;


public class TestSome2 {
    public static HashMap<String, Object> rediskey_type = new HashMap<>();

    @Test
    public void test11() {
        HashMap<String,String> aa = new HashMap();
        aa.put("a", "1");
        aa.put("b", "2");
        String city_name = "";

        System.out.println(city_name);

    }

    @Test
    public void test55() throws Exception {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50);// 最大连接数，连接全部用完，进行等待
        poolConfig.setMinIdle(10); // 最小空余数
        poolConfig.setMaxIdle(30); // 最大空余数
        JedisPool pool = new JedisPool(poolConfig, "172.16.185.54", 6379, 60 * 10000);
        // 从jedis中获取连接资源，并进行权限认证
        Jedis jedis = pool.getResource();
        //Jedis jedis = new Jedis("172.16.185.54", 6379, 60 * 1000);
        String auth = jedis.auth("kaLQlLSU8tZ6GjxLv1JN132");
        System.out.println(auth);
        jedis.select(1);
        Set<String> strings = jedis.keys("*");
        for (String s : strings) {
            System.out.println(s + " ---" + jedis.type(s));
        }
        //List<String> fields = readContext("D:\\Test\\baiduid.txt");//获取所有百度所有站号

        rediskey_type.size();

        for (Map.Entry<String, Object> entries : rediskey_type.entrySet()) {

            List<String> list1h = new ArrayList<>();
            String key = entries.getKey();
            List<String> stationsid = (ArrayList) entries.getValue();
            if (!"forecast3h".equals(key)) {
                continue;
            }
            Map<String, Object> result = new HashMap<String, Object>();
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < stationsid.size(); i++) {//forecast24h//forecast1h
                result.put(stationsid.get(i), pipeline.hget(key, stationsid.get(i)));
                //管道批量跑
                if (i != 0 && i % 4000 == 0) {
                    pipeline.sync();//这段代码获取所有的response
                    System.out.println(key + i);
                }
            }
            pipeline.sync();//这段代码获取所有的response
            pipeline.close();
            for (String keys : result.keySet()) {
                Response res = (Response) result.get(keys);
                Object obj = res.get();
                if (obj == null) {
                    list1h.add((String) keys);
                }
            }
            try {
                writeFileContext(list1h, "D:\\Test\\test11\\" + key + ".txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭资源放回到连接池中
        jedis.close();
        // 真正在开发中，不需要管理连接池资源的,关闭了别人就没法用了。
        pool.close();
    }

    public static void writeFileContext(List<String> strings, String path) throws Exception {
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String l : strings) {
            writer.write(l + "\r\n");
        }
        writer.close();
    }

    public static List<String> readContext(String path) throws Exception {
        List<String> ss = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String strings = null;
        while ((strings = reader.readLine()) != null) {
            ss.add(strings);
        }

        reader.close();
        return ss;
    }


    //    @Before
    public void testread() throws Exception {
        StationCache.properties.get("aa");
        HashMap<String, ArrayList> alldata = new HashMap<>();
        List<String> ss = readContext("C:\\Users\\admin\\Desktop\\fileair\\fileair.txt");
        for (String string : ss) {
            if (string.lastIndexOf("htm") > 0) {
                String[] arr = string.split("/");
                if (arr.length == 9) {
                    String stationid = arr[8].split("\\.")[0];
                    String key = arr[6] + arr[7];
                    ArrayList arrayList = alldata.get(key);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(stationid);
                    alldata.put(key, arrayList);
                }
                if (arr.length == 8) {
                    String stationid = arr[7].split("\\.")[0];
                    String key = arr[6];
                    ArrayList arrayList = alldata.get(key);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(stationid);
                    alldata.put(key, arrayList);
                }

            }
        }
        alldata.size();

        for (String a : alldata.keySet()) {
            System.out.println(a);
        }
        for (Object aa : StationCache.properties.keySet()) {
            if (StationCache.properties.get(aa) != null && !StationCache.properties.get(aa).equals("")) {
                String key = (String) StationCache.properties.get(aa);
                alldata.get(key);
                rediskey_type.put((String) aa, alldata.get(key));
            }
        }
        rediskey_type.size();
    }

    @Test
    public void teststring() {
        Jedis jedis = new Jedis("10.16.57.54", 6379);
        String auth = jedis.auth("u0QdfjalsdDWC3vy1Y5wREQwX7g");
        System.out.println(auth);
        jedis.select(0);
        Set<String> strings = jedis.keys("*");
        for (String s : strings) {
            System.out.println(s + " ---" + jedis.type(s));
            System.out.println();
        }

//        System.out.println(jedis.hgetAll("namebie"));
//        jedis.get("key1");
//        System.out.println( jedis.hmget("namebie","name"));
    }

    String products = "alarm,news_m,fc1h,skixz,skiob,fc1hski,skioball,forecastlong15d,f_ski,wx,alist,silu,observe,qiya,alarmlist,air,forecast3h,forecast,forecastlong,bj_w,f3h,new_pc,pic_pc.video_pc,index,indexs,sk360";

    @Test
    public void testredisstring() throws IOException {

        Jedis jedis = new Jedis("10.16.57.54", 6379);
        String auth = jedis.auth("u0QdfjalsdDWC3vy1Y5wREQwX7g");
        System.out.println(auth);
        jedis.select(2);
        String[] productarr = products.split(",");
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000 * 100; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("temp", "23");
            jsonObject.put("temp2", "30");
            for (String ss : productarr) {
                //pipeline.hset("openapibwxtest::" + ss, i + "", jsonObject.toJSONString());
            }
        }
        pipeline.close();

        Set<String> strings = jedis.keys("*");

        Pipeline pipeline2 = jedis.pipelined();

        List<Object> aa = new ArrayList<>();
        for (String s : strings) {
            if (s.startsWith("openapibwxtest")) {
                for (int i = 0; i < 10000; i++) {
                    Response res = (Response) pipeline2.hget(s, i + "");
//                    Object obj = res.get();
                    aa.add(res);
                }
            }

        }
        pipeline2.sync();
        pipeline2.close();
        for (Object o : aa) {
            Response obj = (Response) o;
            obj.get();
            System.out.println(obj.get());
        }
        jedis.close();
//        System.out.println(jedis.hgetAll("namebie"));
//        jedis.get("key1");
//        System.out.println( jedis.hmget("namebie","name"));
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("172.16.185.55", 6379, 60 * 1000);
        String auth = jedis.auth("kaLQlLSU8tZ6GjxLv1JN132");

        System.out.println(auth);
        jedis.select(1);
        String aa = jedis.hget("forecast1h", "204090100701");
        aa.length();
        Map map = jedis.hgetAll("forecast1h");
        map.size();
        jedis.close();
    }
}
