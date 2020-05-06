package com.bwx.kafka;


import com.bwx.config.Constant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * 测试环境
 */
public class KafkaConsumerTest3 {

    static String kafkahost = Constant.kafka_host;
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer12HTest.class);
    static KafkaConsumer<String, String> consumer = null;

    private static void Init() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "172.16.1.53:9292");
        props.put("bootstrap.servers", kafkahost);
        props.put("group.id", "test113");
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", 100);
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("blue_12h_domestic"
                , "blue_3h_domestic"
        ));
    }

    public static void main(String[] args) {
        Init();
        int i = 1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf("topic = %s  ,offset = %d, key = %s , valueLength = %s%n", record.topic()+"_"+record.partition(), record.offset(), record.key(),record.value().length());

                System.out.printf("topic = %s  ,offset = %d, key = %s,value = %s%n", record.topic(), record.offset(), record.key(), record.value());
                i++;
//                if (i==50){
//                    consumer.commitSync();
//                }else if (i>50){
////                    consumer.close();
//                    i = 1;
////                    Init();
//
//
//                }
//            }

            }


        }


    }
}