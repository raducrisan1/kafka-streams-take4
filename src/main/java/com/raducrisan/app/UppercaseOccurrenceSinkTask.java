package com.raducrisan.app;

import java.util.Collection;
import java.util.Map;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UppercaseOccurrenceSinkTask extends SinkTask {

    private Logger _logger = LoggerFactory.getLogger(UppercaseOccurrenceSinkTask.class);
    private UppercaseOccurrenceSinkConnectorConfig _config;

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public void start(Map<String, String> props) {
        _logger.info("UppercaseOccurrenceSinkTask->start");    
        _config = new UppercaseOccurrenceSinkConnectorConfig(props);
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        try {
            for(SinkRecord record: records) {
                Struct key = (Struct) record.key();
                _logger.info("Sink task: found a record: " + key.toString());
            }
        }
        catch(Exception ex) {
            _logger.error(ex.toString());
        }
    }

    @Override
    public void stop() {

    }

}