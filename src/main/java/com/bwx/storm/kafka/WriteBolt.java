package com.bwx.storm.kafka;

import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.List;
import java.util.Map;

public class WriteBolt extends BaseRichBolt {
    private final static Logger L = Logger.getLogger(WriteBolt.class);

    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String topic = (String) tuple.getValue(0);
        String key = (String) tuple.getValue(3);
        String value = (String) tuple.getValue(4);
        List<Object> aa = tuple.getValues();
        if (key.indexOf("50") > -1) {
            System.out.printf("topic = %s  , key = %s%n", topic, key);
            System.out.println(key+"--------fail----------");
//            collector.fail(tuple);
            return;
        }
        System.out.printf("topic = %s  , key = %s%n", topic, key);
        collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
