package com.bwx.storm.kafka;

import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

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
        System.out.println("tuple0-> " + Thread.currentThread().getName());
        String topic = (String) tuple.getValue(0);
        String key = (String) tuple.getValue(3);
        String value = (String) tuple.getValue(4);
        tuple.getMessageId();
//        collector.emit(tuple,new Values(topic, key.split("_")[1], value));
        collector.emit(new Values(topic, key.split("_")[1], value));
        collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("topic", "key", "value"));
    }


}
