package com.sogoodlabs.planner.realms.service.listener;

import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.realms.service.streams.BasicMessagesStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class RealmsInListener {

    private static Logger log = LoggerFactory.getLogger(RealmsInListener.class.getName());

    @Autowired
    private BasicMessagesStreams streamsService;

    @StreamListener("realms-in")
    public void handleGreetings(@Payload Event event) {
        if(event.getEventType()== EventType.CREATE){
            log.info("Creating event with title "+ event.getPayload());
            Realm realm = new Realm();
            realm.setTitle((String) event.getPayload());

            streamsService.realmsEvents().send(
                    MessageBuilder
                        .withPayload(realm)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build());

            return;
        }
        log.warn("Unknown type of event");
    }

}
