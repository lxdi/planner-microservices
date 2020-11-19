package com.sogoodlabs.planner.dataaccess.listener;

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
    public void realmsIn(@Payload Event event) {
        basicEventHandler.handleRealmsEvent(event);
    }

    @StreamListener("targets-events")
    public void targetsIn(@Payload Event event) {
        basicEventHandler.handleTargetsEvent(event);
    }

    @StreamListener("means-events")
    public void meansIn(@Payload Event event) {
        basicEventHandler.handleMeansEvent(event);
    }

    @StreamListener("layers-events")
    public void layersIn(@Payload Event event) {
        basicEventHandler.handleLayersEvent(event);
    }

}
