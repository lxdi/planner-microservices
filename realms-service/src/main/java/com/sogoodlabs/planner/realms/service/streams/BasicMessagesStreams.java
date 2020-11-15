package com.sogoodlabs.planner.realms.service.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BasicMessagesStreams {

    @Input("realms-in")
    SubscribableChannel realmsIn();

    @Output("realms-events")
    MessageChannel realmsEvents();

}
