package com.bwx.storm.test;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class WordCountBolt extends BaseBasicBolt {
    private Map<String, Long> counts = null;
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        this.counts = new HashMap<String, Long>();
    }

//    @Override
//    public void cleanup() {
//        //拓扑结束执行
//        for (String key : counts.keySet()) {
//            System.out.println(key + " : " + this.counts.get(key));
//        }
//    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String word = tuple.getStringByField("word");
        Long count = this.counts.get(word);
        if (count == null) {
            count = 0L;
        }
        count++;
        this.counts.put(word, count);
        System.out.println(word + " : " + this.counts.get(word));

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
