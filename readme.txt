#################
## Kafka常用命令 ##
#################

* 启动broker：
sh ${KAFKA_HOME}/bin/kafka-server-start.sh -daemon ${KAFKA_HOME}/config/server.properties

* 关闭broker：
sh ${KAFKA_HOME}/bin/kafka-server-stop.sh

* 创建topic：
sh ${KAFKA_HOME}/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic WalterTopic

* 列出topic：
sh ${KAFKA_HOME}/bin/kafka-topics.sh --list --zookeeper localhost:2181

* 查看topic详细信息：
sh ${KAFKA_HOME}/bin/kafka-topics.sh --describe --zookeeper localhost:2181 [--topic WalterTopic]

* 删除topic：
sh ${KAFKA_HOME}/bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic WalterTopic

* 打开一个控制台生产者：
sh ${KAFKA_HOME}/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic WalterTopic

* 打开一个控制台消费者：
sh ${KAFKA_HOME}/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic WalterTopic [--from-beginning]
或
sh ${KAFKA_HOME}/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic WalterTopic [--from-beginning]

