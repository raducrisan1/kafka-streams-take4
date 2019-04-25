#! /usr/bin/env bash

export CLASSPATH=$(find target/ -type f -name '*.jar'| grep '\-with-dependencies' | tr '\n' ':')
mvn clean package
# $CONFLUENT_HOME/bin/connect-standalone \
#     $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties \
#     config/UppercaseOccurrenceSinkConnector.properties
$CONFLUENT_HOME/bin/connect-standalone \
    config/worker-sink-connector.properties \
    config/UppercaseOccurrenceSinkConnector.properties