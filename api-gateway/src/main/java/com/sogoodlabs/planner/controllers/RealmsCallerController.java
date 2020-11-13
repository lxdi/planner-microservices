package com.sogoodlabs.planner.controllers;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.streams.ChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RealmsCallerController {


    private static final Logger log = Logger.getLogger(RealmsCallerController.class.getName());

    @Autowired
    private ChannelsService realmsStreamConnector;

    @GetMapping("/realms/create")
    public void createRealm(@RequestParam("title") String title){

        log.info("Create realm with title " + title);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(title);

        MessageChannel messageChannel = realmsStreamConnector.realmsIn();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

    }

}
