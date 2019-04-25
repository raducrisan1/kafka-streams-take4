package com.raducrisan.app;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class CountUppercaseWords {

    private static KafkaStreams _streams;

    public static void main(String[] args) {
        initialize();
        start();
    }

    public static void initialize() {
        String brokerList = "localhost:9092";
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "count-uppercase-v4");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde.class.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class.getName());
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 2 * 1000);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        final Pattern splitPattern = Pattern.compile("\\W+");
        final Pattern upperCasePattern = Pattern.compile("[A-Z]+");

        final Map<String, String> serdeConfig = Collections
                .singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        final Serde<Key> serdeKey = new SpecificAvroSerde<>();
        serdeKey.configure(serdeConfig, true);

        final Serde<Phrase> serdeValue = new SpecificAvroSerde<>();
        serdeValue.configure(serdeConfig, false);

        final Serde<Word> serdeWord = new SpecificAvroSerde<>();
        serdeWord.configure(serdeConfig, true);

        final Serde<Count> serdeCount = new SpecificAvroSerde<>();
        serdeCount.configure(serdeConfig, false);

        StreamsBuilder sb = new StreamsBuilder();
        final KStream<Key, Phrase> textLines = sb.stream(
            "streams-text-input-v4", 
            Consumed.with(serdeKey, serdeValue));

        KTable<Word, Count> upperCaseCounts = textLines
                .flatMapValues(value -> Arrays.asList(splitPattern.split(value.getContent())))
                .filter((k, v) -> upperCasePattern.matcher(v).matches())
                .groupBy((k, v) -> new Word(v), Grouped.with(serdeWord, Serdes.String()))
                .count()
                .mapValues(v -> new Count(Math.toIntExact(v)));

        upperCaseCounts.toStream().to("streams-text-output-v4", Produced.with(serdeWord, serdeCount));
        _streams = new KafkaStreams(sb.build(), props);
    }

    public static void start() {
        _streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(_streams::close));
    }
}