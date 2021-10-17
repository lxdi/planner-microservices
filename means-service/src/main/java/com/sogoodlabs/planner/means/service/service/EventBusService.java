package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.bus.events.common.channel.MeansChannels;
import com.sogoodlabs.planner.bus.events.common.channel.MeansTargetsRelationsChannels;
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
    private MeansChannels meansEvents;

    @Autowired
    private MeansTargetsRelationsChannels meansTargetsRelationsChannels;

    public void publishMeanEvent(Event event){
        MessageChannel messageChannel = meansEvents.out();
        doPublish(messageChannel, event);
    }

    public void publishLayerEvent(Event event){
        throw new UnsupportedOperationException();
//        MessageChannel messageChannel = streams.layersEvents();
//        doPublish(messageChannel, event);
    }

    public void publishTaskEvent(Event event){
        throw new UnsupportedOperationException();
    }

    public void publishTargetMeanRelationEvent(Event event){
        throw new UnsupportedOperationException();
//        MessageChannel messageChannel = streams.targetMeanRelationEvents();
//        doPublish(messageChannel, event);
    }

    private void doPublish(MessageChannel messageChannel, Event event){
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
