package com.bwx.kafka;


import com.bwx.cache.SunDataCache;
import com.bwx.config.Constant;
import com.sun.org.apache.xml.internal.security.Init;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.kafka.spout.ByTopicRecordTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * 测试环境
 */
public class KafkaConsumerTest {

    static String kafkahost = Constant.kafka_host;
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer12HTest.class);
    static KafkaConsumer<String, String> consumer = null;
    private static void  Init(){
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkahost);
        props.put("group.id", "test113");
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", 100);
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("ocf_1h_test1"
                , "ocf12h_test"
                , "ocf_1h_test",
                "ocf_test",
                "ocf_12h_tourism",
                "ocf_12h_domestic",
                "ocf_12h_tourism",
                "ocf_12h_overseas",
                "sunrise_and_sunset",
                "ocf_3h_domestic_test",
                "baidu_overseas",
                "the_command",
                "ocf_12h_ski",
                "ocf3h_test"));
    }
    public static void main(String[] args) {
        Init();

        int i = 1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s  ,offset = %d, key = %s , valueLength = %s%n", record.topic()+"_"+record.partition(), record.offset(), record.key(),record.value().length());

//                System.out.printf("topic = %s  ,offset = %d, key = %n", record.topic(), record.offset(), record.key());
                i++;
                if (i==50){
//                    consumer.commitSync();
                }else if (i>50){
                    consumer.close();
                    i = 1;
//                    Init();


                }
            }

        }


    }

}
