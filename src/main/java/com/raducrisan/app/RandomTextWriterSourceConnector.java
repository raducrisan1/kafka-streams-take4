package com.raducrisan.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

public class RandomTextWriterSourceConnector extends SourceConnector {

    private RandomTextWriterSourceConnectorConfig _config;

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public void start(Map<String, String> props) {
        _config = new RandomTextWriterSourceConnectorConfig(props);                
    }

    @Override
    public Class<? extends Task> taskClass() {
        return RandomTextWriterSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        ArrayList<Map<String, String>> configs = new ArrayList<>(1);
        for (int i = 0; i < maxTasks; i++) {
            configs.add(_config.originalsStrings());
        }        
        return configs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return RandomTextWriterSourceConnectorConfig.conf();       
    }
}