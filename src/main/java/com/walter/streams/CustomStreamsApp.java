package com.walter.streams;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

public class CustomStreamsApp {
	
	private static final String PROPERTIES_FILE = "/CustomStreamsApp.properties";

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(Class.class.getResourceAsStream(PROPERTIES_FILE));
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		StreamsBuilder builder = new StreamsBuilder();
		builder.<String, String>stream("StreamTopic1").mapValues(line -> {
			return line.replaceAll("[^0-9a-zA-Z]", "");
		}).to("StreamTopic2");

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();
	}
}
