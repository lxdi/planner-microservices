package com.sogoodlabs.planner.targets.service.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BasicMessagesStreams {

    @Output("targets-events")
    MessageChannel realmsEvents();

}
