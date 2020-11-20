package com.sogoodlabs.planner.targets.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.Target;
import com.sogoodlabs.planner.targets.service.client.DataAccessClient;
import com.sogoodlabs.planner.targets.service.service.EventBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
public class MainController {

    private static Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public String heartbeat(){
        return "heartbeat";
    }

    @GetMapping("/version")
    public String version(){
        log.info("Getting version ");
        return "0.0.1";
    }

    @PostMapping("/targets/create")
    public void createTarget(@RequestBody Target target) throws JsonProcessingException {

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

        eventBusService.publishEvent(event);

    }

    @DeleteMapping("/targets/delete")
    public void deleteTarget(@RequestParam String id){

        log.info("Deleting realm with id " + id);

        if(dataAccessClient.getRealmById(id)==null){
            throw new RuntimeException("Realm with id " + id + " doesn't exist");
        }

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishEvent(event);
    }


}
