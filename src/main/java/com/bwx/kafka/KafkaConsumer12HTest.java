package com.bwx.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bwx.obj.WdsiObj;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.bwx.config.Constant.*;


/**
 * 线上业务监听
 */
public class KafkaConsumer12HTest {

    static Map<String, Object> map = new HashMap<>();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.1.53:9292");
        props.put("group.id", "lc1208");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("the_command",
                "ocf_12h_tourism",
                "ocf_12h_domestic",
                "ocf_12h_overseas",
                "blue_12h_domestic",
                "sunrise_and_sunset"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s  ,offset = %d, key = %s%n", record.topic(), record.offset(), record.key());
            }

        }


    }

    private static List<HashMap<String, String>> formatOcf(String json) {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        JSONArray ja = JSON.parseArray(json);
        //head
        JSONObject jo0 = (JSONObject) ja.get(0);
        String publish_time = jo0.getString(HEAD).split("\\s+")[7];
        String fchh = publish_time.substring(8, 10);
        String beijing_time = jo0.getString(HEAD).split("\\s+")[8];
        JSONObject jo_tmp = null;
        if (fchh.equals("20")) {
            jo_tmp = new JSONObject();
        }
        for (int i = 0; i < ja.size(); i++) {
            Object object = ja.get(i);
            JSONObject jo = (JSONObject) object;
            jo_tmp = jo;
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("public_time", publish_time);
            map.put("beijing_time", beijing_time);
            map.put("forecast_time", jo.getString(FC_TIME));
            map.put("fchh", fchh);
            int vti = Integer.parseInt(jo.getString(VTI));
            int vti2 = Integer.parseInt(jo.getString(VTI2));
            map.put("vti", vti + "");
            map.put("vti2", vti2 + "");
//            if (i == 0 && fchh.equals("20")) {
//                int weather1_code = (int) Float.parseFloat(jo_tmp.getString(WEATHER));
//                map.put("weather1_code", weather1_code < 10 ? "0" + weather1_code : "" + weather1_code);
//                map.put("weather1_desc_cn", WdsiObj.dict_wdsi.get("weather_" + map.get("weather1_code")).get("name"));
//                map.put("wd1_code", "" + (int) Float.parseFloat(jo_tmp.getString(WD)));
//                map.put("wd1_desc_cn", WdsiObj.dict_wdsi.get("wd_" + map.get("wd1_code")).get("name"));
//                map.put("ws1_code", "" + (int) Float.parseFloat(jo_tmp.getString(WS)));
//                map.put("ws1_desc_cn", WdsiObj.dict_wdsi.get("ws_" + map.get("ws1_code")).get("name"));
//                map.put("temp", "" + (int) Float.parseFloat(jo.getString(TEMP)));
//                map.put("tempm1", "" + (int) Math.max(Float.parseFloat(jo.getString(TMAX)), Float.parseFloat(jo_tmp.getString(TMAX))));
//                map.put("tempm2", "" + (int) Math.min(Float.parseFloat(jo.getString(TMIN)), Float.parseFloat(jo_tmp.getString(TMIN))));
//                map.put("rain", jo.getString(RAIN));
//
//            } else {
            int weather1_code = (int) Float.parseFloat(jo_tmp.getString(WEATHER));
            map.put("weather1_code", weather1_code < 10 ? "0" + weather1_code : "" + weather1_code);
            map.put("weather1_desc_cn", WdsiObj.dict_wdsi.get("weather_" + map.get("weather1_code")).get("name"));
            map.put("wd1_code", "" + (int) Float.parseFloat(jo_tmp.getString(WD)));
            map.put("wd1_desc_cn", WdsiObj.dict_wdsi.get("wd_" + map.get("wd1_code")).get("name"));
            map.put("ws1_code", "" + (int) Float.parseFloat(jo_tmp.getString(WS)));
            map.put("ws1_desc_cn", WdsiObj.dict_wdsi.get("ws_" + map.get("ws1_code")).get("name"));
            map.put("temp", "" + (int) Float.parseFloat(jo.getString(TEMP)));
            map.put("tempm1", "" + (int) Math.max(Float.parseFloat(jo.getString(TMAX)), Float.parseFloat(jo_tmp.getString(TMAX))));
            map.put("tempm2", "" + (int) Math.min(Float.parseFloat(jo.getString(TMIN)), Float.parseFloat(jo_tmp.getString(TMIN))));
            map.put("rain", jo.getString(RAIN));
            list.add(map);

        }

        return list;
    }

}
