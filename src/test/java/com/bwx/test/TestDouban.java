package com.bwx.test;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bwx.utils.HttpUtil;
import com.sun.jndi.ldap.EntryChangeResponseControl;
import kafka.utils.Json;
import org.junit.Test;

import java.util.*;

/**
 * @Author: biewenxiang
 * @Description:
 * @Date: create in 2020/12/24 10:09
 */
public class TestDouban {
    String url = "http://uz.yurixu.com/uz/index/getList?city=beijing&order=desc&offset=0&limit=200&page=1&pageSize=120&areaPid=&areaid=&lineid=&siteid=&zone=&date=&room=&gender=&source=&rentType=&key=%E7%AB%8B%E6%B0%B4%E6%A1%A5&_=1608772132937";

    @Test
    public void dealjson() {
        Map<String, Integer> count = new TreeMap<>();
        Map<String, Integer> titlecount = new TreeMap<>();
        JSONArray jsonArray2 = new JSONArray();
        for (int m = 1; m <= 5; m++) {
            String content = getContent(m);
            System.out.println(content);
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("dataList");
            jsonArray2.addAll(jsonArray);

        }
        for (int i = 0; i < jsonArray2.size(); i++) {
            JSONObject one = jsonArray2.getJSONObject(i);
            String author = one.getString("author");
            String title = one.getString("title");
            one.getString("url");

            if (titlecount.get(title) == null) {
                titlecount.put(title, 1);
            } else {
                titlecount.put(title, titlecount.get(title) + 1);
            }
            if (count.get(author) == null) {
                count.put(author, 1);
            } else {
                count.put(author, count.get(author) + 1);
            }
        }
        JSONArray jsonArray3 = new JSONArray();
        for (Map.Entry<String,Integer> entry:count.entrySet()){
            if (entry.getValue().intValue()<=1){
                for (int i = 0; i < jsonArray2.size(); i++) {
                    JSONObject one = jsonArray2.getJSONObject(i);
                    String author = one.getString("author");
                    String title = one.getString("title");
                    String url = one.getString("url");
                    if (entry.getKey().equals(author)){
                        jsonArray3.add(one);
                        System.out.println(title+":   "+url);
                    }
                }
            }

        }

//        System.out.println(JSONObject.toJSONString(count));


//        String aa =
    }

    private static String getContent(int pages) {
        String url2 = "http://uz.yurixu.com/uz/index/getList?city=beijing&order=desc&offset=" + (pages - 1) * 120 + "&limit=120&page=" + pages + "&pageSize=120&areaPid=&areaid=&lineid=&siteid=&zone=&date=&room=&gender=&source=&rentType=&key=%E7%AB%8B%E6%B0%B4%E6%A1%A5&_=1608772132937";
        String content = HttpUtil.getUrlContent(url2);

        return content;
    }

}
