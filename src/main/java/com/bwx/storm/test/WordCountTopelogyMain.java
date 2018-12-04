package com.bwx.storm.test;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class WordCountTopelogyMain {
    public static void main(String[] args) {
        try {
            final TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout("spout", new SentenceSpout(), 1);
            builder.setBolt("split", new SplitSentenceBolt(), 2).shuffleGrouping("spout");
            builder.setBolt("count", new WordCountBolt(), 2)
                    .fieldsGrouping("split", new Fields("word"));
            Config config = new Config();
            config.setDebug(false);
            config.setMessageTimeoutSecs(1*5);

            if (args != null && args.length > 0) {
                System.err.println("-----------------------------start");
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } else {
                // 本地模式
                LocalCluster cluster = new LocalCluster();
                config.setNumWorkers(1);
                cluster.submitTopology("word-count2", config, builder.createTopology());
                Utils.sleep(100000);
                cluster.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
