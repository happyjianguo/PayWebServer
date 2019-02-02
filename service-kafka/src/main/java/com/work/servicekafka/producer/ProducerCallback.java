package com.work.servicekafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerCallback implements Callback {

	public ProducerRecord<String, String> producerRecord;
	public int count;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ProducerCallback(ProducerRecord<String, String> producerRecord, int count) {
		super();
		this.producerRecord = producerRecord;
		this.count = count;
	}

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		if (null == exception) {
			long metaOffset = metadata.offset();
			int metaPartition = metadata.partition();
			logger.info("kafka消息发送成功,发送次数:"+count+",offset:"+metaOffset+",partition:"+metaPartition+",消息:"+producerRecord.value());
		} else {
			logger.info("kafka消息发送失败,当前重试次数:"+count);
			if(count >=5) {
				logger.info("重试达到5关闭");
				return ;
			}
			KafkaProducerEnum.Instance.kafkaProducer.send(producerRecord,
					new ProducerCallback(this.producerRecord, ++count));
		}
	}
}
