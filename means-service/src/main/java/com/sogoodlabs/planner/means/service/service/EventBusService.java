package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.means.service.streams.BasicMessagesStreams;
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

    public void publishMeanEvent(Event event){
        MessageChannel messageChannel = streams.meansEvents();
        doPublish(messageChannel, event);
    }

    public void publishLayerEvent(Event event){
        MessageChannel messageChannel = streams.layersEvents();
        doPublish(messageChannel, event);
    }

    public void publishTaskEvent(Event event){
        MessageChannel messageChannel = streams.tasksEvents();
        doPublish(messageChannel, event);
    }

    private void doPublish(MessageChannel messageChannel, Event event){
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
