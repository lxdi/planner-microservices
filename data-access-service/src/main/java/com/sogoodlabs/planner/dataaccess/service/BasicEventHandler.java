package com.sogoodlabs.planner.dataaccess.service;


import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import com.sogoodlabs.planner.dataaccess.listener.BasicListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class BasicEventHandler {

    private static final Logger log = Logger.getLogger(BasicEventHandler.class.getName());

    @Autowired
    private RealmsRepository realmsRepository;

    @Autowired
    private CommonMapper commonMapper;

    public void handleRealmsEvent(Event event){
        if(event.getEventType() == EventType.CREATE){
            Realm realm = commonMapper.mapToEntity((Map)event.getPayload(), new Realm());
            log.info("Creating realm: " + realm.getTitle() + ", id: " + realm.getId());
            realmsRepository.save(realm);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting realm by id: " + event.getPayload());
            realmsRepository.deleteById((String) event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

}
