package com.sogoodlabs.planner.realms.service.config;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class NativeKafkaConsumer {

    private static final Logger LOG = Logger.getLogger(NativeKafkaConsumer.class.getName());

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


}
