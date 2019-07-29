package com.bwx.kafka;

import com.bwx.config.Constant;
import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;

/**
 * 消费者
 */
public class KafkaProvideSun extends Thread {

    public static void main(String args[]) {

        String kafka_host = Constant.kafka_host;
        Properties props = new Properties();
        props.put("bootstrap.servers", kafka_host);
        props.put("acks", "all");//所有实例确认
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        sendMessage(props);
        //sendMessageCallback(props);
        System.out.println("---------");
    }

    private static void sendMessage(Properties props) {
        int i = 101010100;
        String topics =  "sunrise_and_sunset";
        Producer<String, String> producer = new KafkaProducer<>(props);
        while (i >= 0) {
            String value = "[{\"sunrise\":\"01:46\",\"date\":\"20181219\",\"daynum\":1,\"sunset\":\"15:16\"},{\"sunrise\":\"01:46\",\"date\":\"20181220\",\"daynum\":2,\"sunset\":\"15:17\"},{\"sunrise\":\"01:47\",\"date\":\"20181221\",\"daynum\":3,\"sunset\":\"15:17\"},{\"sunrise\":\"01:47\",\"date\":\"20181222\",\"daynum\":4,\"sunset\":\"15:18\"},{\"sunrise\":\"01:48\",\"date\":\"20181223\",\"daynum\":5,\"sunset\":\"15:18\"},{\"sunrise\":\"01:48\",\"date\":\"20181224\",\"daynum\":6,\"sunset\":\"15:19\"},{\"sunrise\":\"01:49\",\"date\":\"20181225\",\"daynum\":7,\"sunset\":\"15:19\"}]";
            Date date = new Date();
//            producer.send(new ProducerRecord<String, String>(topics, "key_" + i+"_"+date.getTime(), value));
            producer.send(new ProducerRecord<String, String>(topics, "key" + i, value));
            try {
                if (i % 1000== 0) {
                    System.out.println(topics);
                    Thread.sleep(10000);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 101011000) {
                i = 101010100;
            } else {
                i++;
            }
        }
        producer.close();
    }

    private static void sendMessageCallback(Properties props) {
        int i = 0;
        Producer<String, String> producer = new KafkaProducer<>(props);
        while (i >= 0) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("ocf_1h_test", "key" + i, "Hello " + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null)
                        System.out.println("the producer has a error:"
                                + e.getMessage());
                    else {
                        System.out.println("The offset of the record we just sent is: "
                                + recordMetadata.offset());
                        System.out.println("The partition of the record we just sent is: "
                                + recordMetadata.partition());
                    }

                }
            });
            try {
                if (i % 5 == 0) {
                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        producer.close();
    }
}
