package com.sogoodlabs.planner.dataaccess.listener;


import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.planner.data.common.data.entities.Realm;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class BasicListener {

    private static final Logger log = Logger.getLogger(BasicListener.class.getName());

    @Autowired
    private RealmsRepository realmsRepository;

    @Autowired
    private CommonMapper commonMapper;

    @StreamListener("realms-events")
    public void realmsIn(@Payload Event event) {
        if(event.getEventType() == EventType.CREATE){
            Realm realm = commonMapper.mapToEntity((Map)event.getPayload(), new Realm());
            log.info("Creating realm: " + realm.getTitle() + ", id: " + realm.getId());
            realmsRepository.save(realm);
            return;
        }
        log.info("Unknown type of event; skipping");
    }

}
