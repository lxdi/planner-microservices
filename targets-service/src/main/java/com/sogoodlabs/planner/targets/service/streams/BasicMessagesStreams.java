package com.sogoodlabs.planner.targets.service.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BasicMessagesStreams {

    @Input("realms-events")
    MessageChannel realmsEvents();

    @Output("targets-events")
    MessageChannel targetsEvents();

}
