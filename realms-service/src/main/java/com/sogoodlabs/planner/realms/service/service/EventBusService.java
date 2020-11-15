package com.sogoodlabs.planner.realms.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.realms.service.streams.BasicMessagesStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class EventBusService {

    @Autowired
    private BasicMessagesStreams streams;

    public void publishEvent(Event event){
        MessageChannel messageChannel = streams.realmsEvents();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
