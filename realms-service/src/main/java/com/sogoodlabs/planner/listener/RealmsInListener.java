package com.sogoodlabs.planner.listener;

import com.sogoodlabs.planner.config.NativeKafkaConsumer;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RealmsInListener {

    private static final Logger LOG = Logger.getLogger(RealmsInListener.class.getName());

    @StreamListener("realms-in")
    public void handleGreetings(@Payload Event event) {
        if(event.getEventType()== EventType.CREATE){
            LOG.info("Creating event with title "+ event.getPayload());
            return;
        }
        LOG.warning("Unknown type of event");
    }

}
