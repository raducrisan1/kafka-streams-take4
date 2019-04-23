package com.raducrisan.app;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class RandomTextWriterSourceConnectorConfig extends AbstractConfig {

    public static final String TOPIC_NAME_CONFIG = "topic.name";
    public static final String TOPIC_NAME_DOC = "The name of the topic";

    public RandomTextWriterSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }   

    public RandomTextWriterSourceConnectorConfig(Map<String, String> config) {
        this(conf(), config);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
            .define(TOPIC_NAME_CONFIG, Type.STRING, Importance.HIGH, TOPIC_NAME_DOC);
    }

    public String getTopicName() {
        return this.getString(TOPIC_NAME_CONFIG);
    }
}