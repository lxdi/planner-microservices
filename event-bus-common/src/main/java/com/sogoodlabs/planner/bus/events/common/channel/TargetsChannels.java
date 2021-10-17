package com.sogoodlabs.planner.bus.events.common.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TargetsChannels {

    @Input("targets-events")
    MessageChannel in();

    @Output("targets-events")
    MessageChannel out();

}
