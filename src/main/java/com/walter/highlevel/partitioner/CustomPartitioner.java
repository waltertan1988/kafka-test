package com.walter.highlevel.partitioner;

import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class CustomPartitioner implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// 控制分区
		
		int partitionCount = cluster.partitionCountForTopic(topic);
		
		try {
			return Integer.parseInt(String.valueOf(key)) % partitionCount;
		}catch(Exception ex) {
			return new Random().nextInt(partitionCount);
		}
	}

	@Override
	public void close() {
		System.out.println("分区关闭时调用...");
	}

}
