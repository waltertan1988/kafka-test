package com.walter.producer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CustomProducer {
	private static final String PROPERTIES_FILE = "/CustomProducer.properties";

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(Class.class.getResourceAsStream(PROPERTIES_FILE));

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 5; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<>(props.getProperty("app.topic"), Integer.toString(i), "Message-" + i);
			
			// 发送消息
			producer.send(record, new Callback() {
				// 消息ack后回调
				@Override
				public void onCompletion(RecordMetadata metadata, Exception ex) {
					if (ex == null) {
						System.out.printf("[PRODUCER-CALLBACK] partition=%s, offset=%s\n", metadata.partition(), metadata.offset());
					}else {
						ex.printStackTrace();
					}
				}
			});
		}

		producer.close();
	}
}
