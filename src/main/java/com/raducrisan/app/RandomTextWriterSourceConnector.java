package com.raducrisan.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

public class RandomTextWriterSourceConnector extends SourceConnector {

    private String _topic;

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public void start(Map<String, String> props) {
        _topic = props.get("TOPIC_NAME");        
    }

    @Override
    public Class<? extends Task> taskClass() {
        return RandomTextWriterSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        ArrayList<Map<String, String>> configs = new ArrayList<>();
        Map<String, String> config = new HashMap<>();
        config.put("TOPIC_NAME", _topic);
        configs.add(config);
        configs.add(config);
        configs.add(config);
        return configs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return null;
    }

    
}