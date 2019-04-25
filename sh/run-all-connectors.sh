#! /usr/bin/env bash
export CLASSPATH=$(find target/ -type f -name '*.jar'| grep '\-with-dependencies' | tr '\n' ':')
$CONFLUENT_HOME/bin/connect-standalone \
    config/worker-source-connector.properties \
    config/RandomTextWriterSourceConnector.properties \
    config/UppercaseOccurrenceSinkConnector.properties