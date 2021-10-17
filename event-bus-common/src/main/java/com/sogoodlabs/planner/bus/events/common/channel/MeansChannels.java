package com.sogoodlabs.planner.bus.events.common.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MeansChannels {

    @Input("means-events")
    MessageChannel in();

    @Output("means-events")
    MessageChannel out();

}
