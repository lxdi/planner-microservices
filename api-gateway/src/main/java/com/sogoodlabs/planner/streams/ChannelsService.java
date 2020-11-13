package com.sogoodlabs.planner.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ChannelsService {

//    @Input("api-gateway-bm-in")
//    SubscribableChannel inboundGreetings();

    @Output("realms-in")
    MessageChannel realmsIn();

}
