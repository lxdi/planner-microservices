package com.sogoodlabs.planner.controllers;

import com.sogoodlabs.planner.streams.ChannelsService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
@RequestMapping(path = "/events")
public class EventsController {

    private static Logger log = LoggerFactory.getLogger(EventsController.class.getName());

    @Autowired
    ChannelsService realmsStreamConnector;

    @PostMapping("/realms")
    public String realms(@RequestBody String event){
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "kafka:9092");

        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("Realms", "General Events", event);

        try {
            log.info("Sending event - " + event);
            producer.send(record);
        } catch (Exception e) {
            log.error("Error while producing event", e);
        }
        return "Success";
    }

    @PostMapping("/realms/stream")
    @ResponseStatus(HttpStatus.OK)
    public void greetings(@RequestBody String event) {

        log.info("Sending event (steams) - " + event);

        MessageChannel messageChannel = realmsStreamConnector.realmsIn();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
