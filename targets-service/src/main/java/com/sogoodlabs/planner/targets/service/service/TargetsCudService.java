package com.sogoodlabs.planner.targets.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.Target;
import com.sogoodlabs.planner.targets.service.client.DataAccessClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TargetsCudService {

    private final static Logger log = LoggerFactory.getLogger(TargetsCudService.class.getName());

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

    public void createTarget(Target target) throws JsonProcessingException {

        String realmId = target.getRealmid();

        if(realmId==null){
            throw new RuntimeException("Target should have a realm");
        }

        if(dataAccessClient.getRealmById(realmId)==null){
            throw new RuntimeException("Error while creating target - realm with id "+ realmId + " doesn't exist");
        }

        log.info("Creating target with title " + target.getTitle());

        target.setId(UUID.randomUUID().toString());

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(target));

        eventBusService.publishTargetsEvent(event);

    }

    public void deleteTarget(String id){

        log.info("Deleting realm with id " + id);

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishTargetsEvent(event);
    }

    public void deleteTargetsByRealmid(String realmid){
        dataAccessClient.getTargetsByRealmid(realmid)
                .forEach(target -> deleteTarget(target.getId()));
    }



}
