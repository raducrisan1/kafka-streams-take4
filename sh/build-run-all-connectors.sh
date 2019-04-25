#! /usr/bin/env bash

export CLASSPATH=$(find target/ -type f -name '*.jar'| grep '\-with-dependencies' | tr '\n' ':')
mvn clean package
# $CONFLUENT_HOME/bin/connect-standalone \
#     $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties \
#     config/RandomTextWriterSourceConnector.properties
$CONFLUENT_HOME/bin/connect-standalone \
    config/worker-source-connector.properties \
    config/RandomTextWriterSourceConnector.properties \
    config/UppercaseOccurrenceSinkConnector.properties