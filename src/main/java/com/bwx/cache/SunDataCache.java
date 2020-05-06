package com.bwx.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SunDataCache {
    public static HashMap<String,String> allDataMap = new HashMap();

    public static void setMap(String key, String value) {
        allDataMap.put(key, value);
    }

    public static String getMap(String key) {

        return (String) allDataMap.get(key);
    }
    public static List<String> getSun(String key){
        List<String> sunlist = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            String json = (String) allDataMap.get(key);
            JSONArray ja = JSON.parseArray(json);
            sunlist.add(ja.getJSONObject(0).getString("sunrise"));
            sunlist.add(ja.getJSONObject(0).getString("sunset"));
            sunlist.add(ja.getJSONObject(1).getString("sunrise"));
            sunlist.add(ja.getJSONObject(1).getString("sunset"));
            for (Object object : ja) {
                JSONObject jo = (JSONObject) object;
                String date =jo.getString("date");
                try {
                    c.setTime(sdf.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(c.get(Calendar.DAY_OF_WEEK)==1||c.get(Calendar.DAY_OF_WEEK)==7){
                    sunlist.add(jo.getString("sunrise"));
                    sunlist.add(jo.getString("sunset"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            sunlist = new ArrayList<String>();
            sunlist.add("");
            sunlist.add("");
            sunlist.add("");
            sunlist.add("");

            sunlist.add("");
            sunlist.add("");
            sunlist.add("");
            sunlist.add("");
        }

        return sunlist;
    }
}
