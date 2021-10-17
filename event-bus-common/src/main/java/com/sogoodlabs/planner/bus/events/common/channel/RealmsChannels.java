package com.sogoodlabs.planner.bus.events.common.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RealmsChannels {

    @Input("realms-events")
    MessageChannel in();

    @Output("realms-events")
    MessageChannel out();

}
