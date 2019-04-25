package com.raducrisan.app;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class UppercaseOccurrenceSinkConnectorConfig extends AbstractConfig {
    
    public static final String TOPICS_CONFIG = "topics";
    public static final String TOPICS_DOC = "The topic list";

    public UppercaseOccurrenceSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }   

    public UppercaseOccurrenceSinkConnectorConfig(Map<String, String> config) {
        this(conf(), config);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
            .define(TOPICS_CONFIG, Type.STRING, Importance.HIGH, TOPICS_DOC);
    }

    public String getTopicName() {
        return this.getString(TOPICS_CONFIG);
    }
}