package com.sogoodlabs.planner.dataaccess.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageStreams {

    @Input("realms-events")
    MessageChannel realmsIn();

    @Input("targets-events")
    MessageChannel targetsIn();

    @Input("means-events")
    MessageChannel meansEvents();

    @Input("layers-events")
    MessageChannel layersEvents();

    @Input("tasks-events")
    MessageChannel tasksEvents();

}
