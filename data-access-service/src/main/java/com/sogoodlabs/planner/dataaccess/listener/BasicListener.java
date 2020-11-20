package com.sogoodlabs.planner.dataaccess.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.dataaccess.service.BasicEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BasicListener {

    private static final Logger log = Logger.getLogger(BasicListener.class.getName());

    @Autowired
    private BasicEventHandler basicEventHandler;

    @StreamListener("realms-events")
    public void realmsIn(@Payload Event event) throws Exception {
        basicEventHandler.handleRealmsEvent(event);
    }

    @StreamListener("targets-events")
    public void targetsIn(@Payload Event event) throws Exception {
        basicEventHandler.handleTargetsEvent(event);
    }

    @StreamListener("means-events")
    public void meansIn(@Payload Event event) throws Exception {
        basicEventHandler.handleMeansEvent(event);
    }

    @StreamListener("layers-events")
    public void layersIn(@Payload Event event) throws Exception {
        basicEventHandler.handleLayersEvent(event);
    }

}
