package com.walter.highlevel.consumer;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class CustomConsumer {

	private static final String PROPERTIES_FILE = "/CustomProducer.properties";
	private static final int COMMIT_BATCH_SIZE = 1;

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(Class.class.getResourceAsStream(PROPERTIES_FILE));

		// 定义consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		// 消费者订阅的topic, 可同时订阅多个
		consumer.subscribe(Arrays.asList(props.getProperty("app.topic")));

		List<ConsumerRecord<String, String>> commitBuffer = new ArrayList<>();
		try {
			while (true) {
				// 从broker拉取数据
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				
				for (ConsumerRecord<String, String> record : records) {
					commitBuffer.add(record);
				}
				
				if (commitBuffer.size() >= COMMIT_BATCH_SIZE) {
					for(ConsumerRecord<String, String> record : records) {
						System.out.printf("[CONSUMER] offset=%d, key=%s, value=%s\n", record.offset(), record.key(), record.value());
					}
					
					// 同步方式提交offset
					consumer.commitSync();
					commitBuffer.clear();
				}
			}
		}finally {
			consumer.close();
		}
	}
}
