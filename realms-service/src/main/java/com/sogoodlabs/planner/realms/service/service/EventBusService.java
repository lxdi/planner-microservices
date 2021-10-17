package com.sogoodlabs.planner.realms.service.service;

import com.sogoodlabs.planner.bus.events.common.channel.RealmsChannels;
import com.sogoodlabs.planner.data.common.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class EventBusService {

    @Autowired
    private RealmsChannels realmsChannels;

    public void publishEvent(Event event){
        MessageChannel messageChannel = realmsChannels.out();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
