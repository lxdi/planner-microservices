package com.sogoodlabs.planner.listener;

import com.sogoodlabs.planner.config.NativeKafkaConsumer;
import com.sogoodlabs.planner.data.common.data.entities.Realm;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.streams.BasicMessagesStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.logging.Logger;

@Service
public class RealmsInListener {

    private static final Logger LOG = Logger.getLogger(RealmsInListener.class.getName());

    @Autowired
    private BasicMessagesStreams streamsService;

    @StreamListener("realms-in")
    public void handleGreetings(@Payload Event event) {
        if(event.getEventType()== EventType.CREATE){
            LOG.info("Creating event with title "+ event.getPayload());
            Realm realm = new Realm();
            realm.setTitle((String) event.getPayload());

            streamsService.realmsEvents().send(
                    MessageBuilder
                        .withPayload(realm)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build());

            return;
        }
        LOG.warning("Unknown type of event");
    }

}
