package com.sogoodlabs.planner.means.service.listener;


import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.means.service.service.MeansCUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BasicListener {

    @Autowired
    private MeansCUDService meansCUDService;

    @StreamListener("realms-events")
    public void realmsIn(@Payload Event event) {
        if(event.getEventType()== EventType.DELETE) {
            meansCUDService.deleteMeansForRealm(event.getPayload());
        }
    }

}
