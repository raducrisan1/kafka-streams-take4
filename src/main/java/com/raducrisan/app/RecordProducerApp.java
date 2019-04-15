package com.raducrisan.app;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class RecordProducerApp {

    private static Producer<Key, Phrase> _producer;
    private static Logger _logger = LoggerFactory.getLogger(RecordProducerApp.class);
    private static Thread _mainThread = Thread.currentThread();
    private static Object _sync = new Object();
    private static boolean _exitNow = false;

    public static void main(String[] args) {
        initialize();
        setupShutdownHandler();

        try {
            while (true) {
                for (int i = 0; i < 10; i++) {
                    addRecord(new Date().getTime(), "This IS a LIST of CAPITAL case WORDS");
                }
                _producer.flush();
                synchronized (_sync) {
                    if (_exitNow)
                        break;
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Done.");
    }

    private static void initialize() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:9092,kafka-2:9093,kafka-3:9094");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "count-uppercase-2");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        _producer = new KafkaProducer<Key, Phrase>(props);
    }

    private static void addRecord(Long key, String content) throws InterruptedException {
        ProducerRecord<Key, Phrase> record = new ProducerRecord<>("streams-text-input-v2", new Key(key),
                new Phrase(content));

        try {
            RecordMetadata metadata = _producer.send(record).get();
            System.out.println("Record sent with key:" + key + ":" + content + " to partition " + metadata.partition());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void setupShutdownHandler() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                _logger.info("App shutdown has been requested.");
                synchronized (_sync) {
                    _exitNow = true;
                    _mainThread.interrupt();
                }
                try {
                    _mainThread.join();
                } catch (InterruptedException e) {
                    _logger.info("shutdown hook InterruptedException handled");
                }
            }
        });
    }
}
