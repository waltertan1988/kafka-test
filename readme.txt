#################
## Kafka常用命令 ##
#################

* 启动broker：
sh ${KAFKA_HOME}/bin/kafka-server-start.sh -daemon ${KAFKA_HOME}/config/server.properties

* 关闭broker：
sh ${KAFKA_HOME}/bin/kafka-server-stop.sh

* 创建topic：
sh ${KAFKA_HOME}/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic WalterTopic
