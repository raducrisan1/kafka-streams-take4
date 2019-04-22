package com.raducrisan.app;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

public class RecordConsumerApp {

    private static Consumer<String, Integer> _consumer;
    private static Logger _logger = LoggerFactory.getLogger(RecordConsumerApp.class);
    private static Thread _mainThread = Thread.currentThread();

    public static void main(String[] args) {
        _logger.info("The app has been started");
        initialize();
        setupShutdownHandler();
        start();
        _logger.info("The app has been closed");
        System.out.println("The app has been closed");
    }

    private static void setupShutdownHandler() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                _logger.info("App shutdown has been requested.");
                _consumer.wakeup();

                try {
                    _mainThread.join();
                    _logger.info("Wait completed");
                } catch (InterruptedException e) {
                    _logger.info("shutdown hook InterruptedException handled");
                }
            }
        });
    }

    private static void initialize() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "count-uppercase-consumer-4");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Serdes.String().deserializer().getClass().getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Serdes.Integer().deserializer().getClass().getName());
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        
        _consumer = new KafkaConsumer<>(props);
        _consumer.subscribe(Collections.singletonList("streams-text-output-v4"));
    }

    private static void start() {
        try {
            while (true) {
                ConsumerRecords<String, Integer> consumerRecords = _consumer.poll(Duration.ofSeconds(1));
                if (consumerRecords.count() == 0)
                    continue;
                for (ConsumerRecord<String, Integer> record : consumerRecords) {
                    _logger.info("Retrieved " + record.key() + ":" + record.value());
                }
                _consumer.commitAsync();
            }
        } catch (WakeupException e) {
        } finally {
            _consumer.close();
        }
    }
}