package com.bwx.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DateCacheTest {
    private  static ConcurrentHashMap allDataMap = new ConcurrentHashMap();

    public static void setMap(String key,String value){
        allDataMap.put(key,value);
    }
    public static String getMap(String key){
       return (String) allDataMap.get(key);
    }
}
