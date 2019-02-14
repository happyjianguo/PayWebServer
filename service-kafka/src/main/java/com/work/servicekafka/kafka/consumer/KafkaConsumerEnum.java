package com.work.servicekafka.kafka.consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum KafkaConsumerEnum {
	Instance();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private KafkaConsumer<String, String> kafkaConsumer;

	private KafkaConsumerEnum() {
		Properties properties = new Properties();
		// kafka集群地址 , 形式为 ip:port,ip:port,ip:port
		properties.put("bootstrap.servers","192.168.61.129:9092");
		// 消费者偏移量自动提交
		properties.put("enable.auto.commit", "false");
		// 消费者偏移量自动提交周期
		properties.put("auto.commit.interval.ms", "100");
		// 消费者故障超时
		properties.put("session.timeout.ms", "60000");
		// 实现Serializer接口的序列化类的键
		properties.put("key.deserializer", StringDeserializer.class);
		// 实现Serializer接口的序列化类的值
		properties.put("value.deserializer", StringDeserializer.class);
		// 消费者所属组的id
		properties.put("group.id", "ewm");
		// 偏移量设置
		properties.put("auto.offset.reset", "latest");
		// 消费者偏移量提交间隔时间控制
		properties.put("heartbeat.interval.ms", "20000");
		properties.put("request.timeout.ms", "70000");

		kafkaConsumer = new KafkaConsumer<String, String>(properties);
	}

	public void consumer() {
		kafkaConsumer.subscribe(Arrays.asList("test_xkl_r2p2"));
		ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
		// 从kafka消费者获取数据
		for (TopicPartition topicPartition : records.partitions()) {
			List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
			long lastOffset = partitionRecords.get(0).offset();
			try {
				for (int i = 0; i < partitionRecords.size(); i++) {
					ConsumerRecord<String, String> partitionRecord = partitionRecords.get(i);
					String topic = partitionRecord.topic();
					lastOffset = partitionRecord.offset();
					Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
					currentOffsets.put(topicPartition, new OffsetAndMetadata(lastOffset + 1L));
					String recordValue = partitionRecord.value();
					logger.info("消费消息：" + recordValue);
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		while(true) {
			Thread.sleep(2000L);
			KafkaConsumerEnum.Instance.consumer();
		}
	}
}
