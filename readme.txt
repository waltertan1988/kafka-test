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

* 查看topic详细信息
sh ${KAFKA_HOME}/bin/kafka-topics.sh --describe --zookeeper localhost:2181 [--topic WalterTopic]

* 删除topic：
sh ${KAFKA_HOME}/bin/kafka-run-class.sh --delete --zookeeper localhost:2181 --topic WalterTopic