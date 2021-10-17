package com.sogoodlabs.planner.bus.events.common.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MeansTargetsRelationsChannels {

    @Input("mean-target-relations-events")
    MessageChannel in();

    @Output("mean-target-relations-events")
    MessageChannel out();

}
