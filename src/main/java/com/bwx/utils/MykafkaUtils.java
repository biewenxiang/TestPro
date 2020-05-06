package com.bwx.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * Kafka工具类
 * @author zhichen
 *
 */
public class MykafkaUtils {

	/**
	 * 获取kafka配置参数
	 * @return
	 */
	public static Map<String, Object> getkafkaParams1() {
		Map<String, Object> kafkaParams = new HashMap<>();
		// Kafka服务监听端口
		kafkaParams.put("bootstrap.servers", "mongodb_kafka01:9092,mongodb_kafka02:9092,mongodb_kafka03:9092");
		kafkaParams.put("acks", "all");
		kafkaParams.put("retries", 0);
		kafkaParams.put("batch.size", 16384);
		kafkaParams.put("linger.ms", 1);
		kafkaParams.put("buffer.memory", 33554432);
		kafkaParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaParams.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		return kafkaParams;
	}
	
	/**
	 * 获取kafka配置参数
	 * @return
	 */
	public static Map<String, Object> getkafkaParams2() {
		Map<String, Object> kafkaParams = new HashMap<>();
		
		kafkaParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaParams.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		
		// Kafka服务监听端口
		kafkaParams.put("bootstrap.servers", "mongodb_kafka01:9092,mongodb_kafka02:9092,mongodb_kafka03:9092");
		// 指定kafka输出key的数据类型及编码格式（默认为字符串类型编码格式为uft-8）
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		// 指定kafka输出value的数据类型及编码格式（默认为字符串类型编码格式为uft-8）
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		// 指定从latest(最新,其他版本的是largest这里不行)还是smallest(最早)处开始读取数据，latest,
		// earliest, none
		kafkaParams.put("auto.offset.reset", "latest");
		// 如果true,consumer定期地往zookeeper写入每个分区的offset
		kafkaParams.put("enable.auto.commit", false);
		return kafkaParams;
	}
}
