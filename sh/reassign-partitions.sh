kafka-reassign-partitions --zookeeper localhost:2181 --reassignment-json-file sh/repartition-input.json --execute
kafka-reassign-partitions --zookeeper localhost:2181 --reassignment-json-file sh/repartition-input.json --verify
