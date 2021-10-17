package com.sogoodlabs.planner.dataaccess.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.dataaccess.service.BasicEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class BasicListener {

    private static Logger log = LoggerFactory.getLogger(BasicListener.class.getName());

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

    @StreamListener("mean-target-relations-events")
    public void targetMeanRelationIn(@Payload Event event) throws Exception {
        basicEventHandler.handleTargetMeanRelationsEvent(event);
    }

}
