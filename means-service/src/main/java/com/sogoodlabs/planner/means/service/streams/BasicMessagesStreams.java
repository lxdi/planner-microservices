package com.sogoodlabs.planner.means.service.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BasicMessagesStreams {

    @Input("realms-events")
    MessageChannel realmsEvents();

    @Output("means-events")
    MessageChannel meansEvents();

    @Output("layers-events")
    MessageChannel layersEvents();

    @Output("tasks-events")
    MessageChannel tasksEvents();

    @Output("mean-target-relations-events")
    MessageChannel targetMeanRelationEvents();

}
