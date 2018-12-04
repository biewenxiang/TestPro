package com.bwx.kafka;


import com.bwx.config.Constant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 测试环境
 */
public class KafkaConsumerTest {

    static String kafkahost = Constant.kafka_host;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkahost);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");

        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("ocf_1h_test1"
                , "ocf12h_test"
                , "ocf_1h_test",
                "ocf_test",
                "ocf_12h_tourism",
                "ocf_12h_domestic",
                "ocf_12h_tourism",
                "ocf_12h_overseas",
                "the_command",
                "ocf3h_test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s  ,offset = %d, key = %s, value = %s%n", record.topic(), record.offset(), record.key(), record.value());
            }

        }


    }

}
