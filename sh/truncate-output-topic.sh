kafka-configs --zookeeper localhost:2181 --entity-type topics --alter --entity-name streams-text-output-v4 --add-config retention.ms=1000
#sleep 6
kafka-configs --zookeeper localhost:2181 --entity-type topics --alter --entity-name streams-text-output-v4 --add-config retention.ms=604800000