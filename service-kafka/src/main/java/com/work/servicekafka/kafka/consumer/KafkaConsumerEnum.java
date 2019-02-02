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
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.61.129:9092");
		// 消费者偏移量自动提交
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		// 消费者偏移量自动提交周期
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		// 消费者故障超时
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000");
		// 实现Serializer接口的序列化类的键
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 实现Serializer接口的序列化类的值
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 消费者所属组的id
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "ewm");
		// 偏移量设置
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		// 消费者偏移量提交间隔时间控制
		properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "20000");
		properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "70000");

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
