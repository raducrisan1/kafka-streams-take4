#! /usr/bin/env bash
export CLASSPATH=$(find target/ -type f -name '*.jar'| grep '\-with-dependencies' | tr '\n' ':')
$CONFLUENT_HOME/bin/connect-standalone \
    $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties \
    config/UppercaseOccurrenceSinkConnector.properties