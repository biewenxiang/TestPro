package com.bwx.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bwx.obj.WdsiObj;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.bwx.config.Constant.*;


/**
 * 线上业务监听
 */
public class Kafka75TestAll {

    private static Logger logger = LoggerFactory.getLogger(Kafka75TestAll.class);
    static Map<String, Object> map = new HashMap<>();

    public static void main(String[] args) {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "172.16.1.53:9292");
//        props.put("bootstrap.servers", Constant.kafka_host);

        props.put("bootstrap.servers", "mongodb_kafka01:9092,mongodb_kafka02:9092,mongodb_kafka03:9092");

        props.put("group.id", "test20191211");
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "latest");
//        props.put("auto.offset.reset", "earliest");

        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("baidu_overseas",
                "blue_12h_domestic",
                "blue_3h_domestic",
                "hello",
                "index_24h_county_idtime",
                "index_24h_domestic",
                "index_24h_town_idtime",
                "nmc_24h_bj",
                "obs_today",
                "obs_yesterday",
                "ocf_12h_airport_idtime",
                "ocf_12h_baidu_idtime",
                "ocf_12h_beltroad_idtime",
                "ocf_12h_client_idtime",
                "ocf_12h_domestic",
                "ocf_12h_domestic_idtime",
                "ocf_12h_domesticidtime",
                "ocf_12h_expressway_idtime",
                "ocf_12h_golf_idtime",
                "ocf_12h_island",
                "ocf_12h_island_idtime",
                "ocf_12h_ocf12h_idtime",
                "ocf_12h_other_idtime",
                "ocf_12h_overseas",
                "ocf_12h_overseas_cut",
                "ocf_12h_overseas_idtime",
                "ocf_12h_port_idtime",
                "ocf_12h_ski",
                "ocf_12h_ski_idtime",
                "ocf_12h_tourism",
                "ocf_12h_tourism_idtime",
                "ocf_12h_town_idtime",
                "ocf_12h_travel_idtime",
                "ocf_12h_twc_idtime",
                "ocf_1h_airport_idtime",
                "ocf_1h_baidu_idtime",
                "ocf_1h_beltroad_idtime",
                "ocf_1h_client_idtime",
                "ocf_1h_domestic",
                "ocf_1h_domestic_idtime",
                "ocf_1h_expressway_idtime",
                "ocf_1h_golf_idtime",
                "ocf_1h_island_idtime",
                "ocf_1h_other_idtime",
                "ocf_1h_overseas",
                "ocf_1h_overseas_idtime",
                "ocf_1h_port_idtime",
                "ocf_1h_ski_idtime",
                "ocf_1h_tourism",
                "ocf_1h_tourism_idtime",
                "ocf_1h_town_idtime",
                "ocf_1h_travel_idtime",
                "ocf_1h_twc_idtime",
                "ocf_24h_airport_idtime",
                "ocf_24h_baidu_idtime",
                "ocf_24h_beltroad_idtime",
                "ocf_24h_client_idtime",
                "ocf_24h_county_idtime",
                "ocf_24h_domestic_idtime",
                "ocf_24h_expressway_idtime",
                "ocf_24h_golf_idtime",
                "ocf_24h_island_idtime",
                "ocf_24h_other_idtime",
                "ocf_24h_overseas_idtime",
                "ocf_24h_port_idtime",
                "ocf_24h_ski_idtime",
                "ocf_24h_tourism_idtime",
                "ocf_24h_town_idtime",
                "ocf_24h_travel_idtime",
                "ocf_24h_twc_idtime",
                "ocf_3h_airport_idtime",
                "ocf_3h_baidu_idtime",
                "ocf_3h_beltroad_idtime",
                "ocf_3h_client_idtime",
                "ocf_3h_domestic",
                "ocf_3h_domestic_idtime",
                "ocf_3h_expressway_idtime",
                "ocf_3h_golf_idtime",
                "ocf_3h_island",
                "ocf_3h_island_idtime",
                "ocf_3h_other_idtime",
                "ocf_3h_overseas",
                "ocf_3h_overseas_idtime",
                "ocf_3h_port_idtime",
                "ocf_3h_ski",
                "ocf_3h_ski_idtime",
                "ocf_3h_tourism",
                "ocf_3h_tourism_idtime",
                "ocf_3h_town_idtime",
                "ocf_3h_travel_idtime",
                "ocf_3h_twc_idtime",
                "sunrise_and_sunset",
                "the_command"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            Map map = consumer.listTopics();
            map.size();
            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf("topic = %s  ,offset = %d, key = %s%n", record.topic(), record.offset(), record.key());
                logger.info("topic = {}  ,offset = {}, key = {} , val = {}", record.topic(), record.offset(), record.key(), record.value());

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
