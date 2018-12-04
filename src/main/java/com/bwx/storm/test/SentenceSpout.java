
package com.bwx.storm.test;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;

public class SentenceSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String[] sentences = {
            "Apache Storm is a free and open source distributed realtime computation system",
            "Storm makes it easy to reliably process unbounded streams of data",
            "doing for realtime processing what Hadoop did for batch processing",
            "Storm is simple", "can be used with any programming language",
            "and is a lot of fun to use 我"};
    private int index = 0;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        this.collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        if (index >= sentences.length) {
            Utils.sleep(1);
//            this.collector.emit(new Values("没有啦 真逗没有 打的 我们的股价"));
            return;
        }
        //发送字符串
        this.collector.emit(new Values(sentences[index]));
        index++;
        Utils.sleep(1000 * 10);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
//定义输出字段描述
        outputFieldsDeclarer.declare(new Fields("sentence"));

    }

    public void ack(Object msgId) {


    }
}
