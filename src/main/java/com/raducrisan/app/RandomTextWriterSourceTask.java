package com.raducrisan.app;

import java.time.Instant;
import java.util.*;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomTextWriterSourceTask extends SourceTask {

    private Logger _logger = LoggerFactory.getLogger(RandomTextWriterSourceTask.class);
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
    public List<SourceRecord> poll() throws InterruptedException {
        ArrayList<SourceRecord> records = new ArrayList<>();
        
        Map<String, ?> sourcePartition = Collections.singletonMap("source", "random");
        Map<String, ?> sourceOffset = Collections.singletonMap("position", (int) (Math.random() * 10));
        Phrase p = new Phrase("THIS IS a PHRASE with SOME Uppercase WORDS");
        Key k = new Key(Instant.now().toEpochMilli());
        SourceRecord sr = new SourceRecord(
            sourcePartition,
            sourceOffset, 
            _config.getTopicName(),
            null,
            ConnectSchemas.KEY_SCHEMA,
            buildRecordKey(k),
            ConnectSchemas.VALUE_SCHEMA,
            buildRecordValue(p), 
            k.getId());
        records.add(sr);
        _logger.info(String.format("A record with ID: %d has been added.", k.getId()));
        Thread.sleep(1500);
        return records;
    }

    private Struct buildRecordKey(Key k) {
        Struct key = new Struct(ConnectSchemas.KEY_SCHEMA)
        .put(ConnectSchemas.ID_FIELD, k.getId());
        return key;
    }

    private Struct buildRecordValue(Phrase p) {
        Struct value = new Struct(ConnectSchemas.VALUE_SCHEMA)
        .put(ConnectSchemas.CONTENT_FIELD, p.getContent());
        return value;
    }

    @Override
    public void stop() {

    }

}