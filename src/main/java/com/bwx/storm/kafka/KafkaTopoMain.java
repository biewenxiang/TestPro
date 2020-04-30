package com.bwx.storm.kafka;

import com.bwx.config.Constant;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KafkaTopoMain {
    static String kafkahost = Constant.kafka_host;


    public static void main(String[] args) {

        try {
            final TopologyBuilder tp = new TopologyBuilder();


            List<String> topiclist = new ArrayList<String>();

            topiclist = Arrays.asList("ocf_1h_test1"
                    , "ocf12h_test"
                    , "ocf_1h_test",
                    "ocf_test",
                    "ocf_12h_tourism",
                    "ocf_12h_domestic",
                    "sunrise_and_sunset",
                    "ocf_12h_tourism",
                    "ocf_12h_overseas",
                    "the_command",
                    "ocf3h_test");
            KafkaSpoutConfig kafkaConfig = KafkaSpoutConfig.builder(kafkahost, topiclist)
                    .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.LATEST)
                    .setGroupId("test4"+System.currentTimeMillis())
                    .build();


            tp.setSpout("kafka_spout", new KafkaSpout<>(kafkaConfig), 1);

//            tp.setBolt("write_bolt", new WriteBolt()).localOrShuffleGrouping("kafka_spout");
            tp.setBolt("write_bolt2", new WriteBolt2(), 4).shuffleGrouping("kafka_spout");
//            tp.setBolt("write_bolt", new WriteBolt(),8).shuffleGrouping("kafka_spout");

//            tp.setBolt("write_bolt", new WriteBolt(),4).fieldsGrouping("kafka_spout",new Fields("kafka_spout"));


            Config conf = new Config();
            conf.setDebug(false);
            conf.setMessageTimeoutSecs(60 * 5);
            if (args != null && args.length > 0) {
                conf.setNumWorkers(2);
                StormSubmitter.submitTopology(args[0], conf, tp.createTopology());
            } else {
                LocalCluster cluster = new LocalCluster();
                conf.setNumWorkers(2);
                cluster.submitTopology("test", conf, tp.createTopology());
                Utils.sleep(100000);
                cluster.killTopology("test");
                cluster.shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
