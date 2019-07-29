package com.bwx.storm.kafka;

import com.bwx.cache.DateCacheTest;
import com.bwx.cache.SunDataCache;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import sun.security.provider.Sun;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WriteBolt2 extends BaseRichBolt {
    private final static Logger L = Logger.getLogger(WriteBolt2.class);

    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        System.out.println("tuple0-> " + Thread.currentThread().getName());
        String topic = (String) tuple.getStringByField("topic");
        String key = (String) tuple.getStringByField("key");
        String value = (String) tuple.getStringByField("value");
        String data_type = null;
        boolean is_return = false;
        if (topic.equals("ocf_12h_domestic")) {
            data_type = "forecast_15d_24h_internal";
            DateCacheTest.setMap(key, value);
        } else if (topic.equals("blue_12h_domestic")) {
            data_type = "forecast_15d_24h_internal";
        } else if (topic.equals("ocf_12h_tourism") || topic.equals("ocf_12h_island")) {
            data_type = "forecast_24h_travel";
        } else if (topic.equals("ocf_12h_overseas") || topic.equals("baidu_overseas")) {
            DateCacheTest.setMap(key, value);
            data_type = "forecast_24h_external";
        } else if (topic.equals("sunrise_and_sunset")) {
            SunDataCache.setMap(key, value);
            is_return = true;
        } else if (topic.equals("nmc_24h_bj")) {
            data_type = "city_forecast";
        }
        if (topic.equals("ocf_12h_domestic")) {
            try {
                System.out.println("topic -> " + topic + " " + key + " " + SunDataCache.getSun(key).size() + " ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }
}
