package com.sogoodlabs.planner.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Component
public class InitKafkaConsumer {

    private static final Logger LOG = Logger.getLogger(InitKafkaConsumer.class.getName());

//    @PostConstruct
//    public void init(){
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "kafka:9092");
//        props.put("group.id", "Realms");
//        props.put("key.deserializer",
//                "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer",
//                "org.apache.kafka.common.serialization.StringDeserializer");
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//
//        LOG.info("Subscribing to the topic");
//        consumer.subscribe(Collections.singletonList("Realms"));
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        // Runnable, return void, nothing, submit and run the task async
//        executor.submit(() -> {
//            try {
//                while (true) {
//                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(2));
//                    for (ConsumerRecord<String, String> record : records)
//                    {
//                        LOG.info("Event received - "+record.value());
//                    }
//                }
//            } finally {
//                consumer.close();
//            }
//        });
//
//    }

    @StreamListener("api-gateway-bm-in")
    public void handleGreetings(@Payload String message) {
        LOG.info("Event received (stream): "+message);
    }


}
