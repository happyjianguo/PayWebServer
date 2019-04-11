package com.work.servicekafka.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum KafkaProducerEnum {
	Instance();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public KafkaProducer<String,String> kafkaProducer;
	
	private KafkaProducerEnum() {
		Properties properties = new Properties();
        //kafka集群地址 , 形式为 ip:port,ip:port,ip:port
        properties.put("bootstrap.servers", "192.168.61.129:9092");
        //发送失败重试次数
        properties.put("retries", "0");
        //批处理消息允许最大值
        properties.put("batch.size", "2097152");
        //批处理消息延迟发送毫秒数
        properties.put("linger.ms", "0");
        //broker消息缓冲区大小
        properties.put("buffer.memory", "33554432");
        //实现Serializer接口的序列化类的键
        properties.put("key.serializer", StringSerializer.class);
        //实现Serializer接口的序列化类的值
        properties.put("value.serializer", StringSerializer.class);
        //消息发送后确认机制
        properties.put("acks", "all");
        //生产者阻塞时间
        properties.put("max.block.ms", "3000");
        //请求超时时间
        properties.put("request.timeout.ms", "3000");
        //metadata时间
        properties.put("metadata.fetch.timeout.ms", "3000");

        kafkaProducer= new KafkaProducer<String,String>(properties);
	}
	
	public boolean produce(String data){
		try {
			//1.通过回调来处理返回消息，可增加吞吐量,还可以设置重发以及重发次数
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test_xkl_r2p2",data);
			kafkaProducer.send(producerRecord,new ProducerCallback(producerRecord, 1));
			
			//2.同步判断返回消息，性能较差
//			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("ewm-order-info_r3p3",data);
//			Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
//			RecordMetadata recordMetadata = future.get();
//			long offset = recordMetadata.offset();
//			int partition = recordMetadata.partition();
//			logger.info("kafka发送消息成功,offset："+offset+",partition:"+partition);
			kafkaProducer.flush();
			return true;
		} catch (Exception e) {
			logger.error("发送kafka异常"+e.getMessage(),e);
			return false;
		} 
		
	}

	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 1; i <= 100; i++) {
			KafkaProducerEnum.Instance.produce(i+"");
			Thread.sleep(1000L);
		}
	}
}
