package com.sogoodlabs.planner.realms.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.realms.service.client.DataAccessClient;
import com.sogoodlabs.planner.realms.service.service.EventBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/realms")
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class.getName());

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
        return "0.0.1";
    }

    @PostMapping("/create")
    public void createRealm(@RequestBody Realm realm) throws JsonProcessingException {

        log.info("Creating realm with title " + realm.getTitle());

        realm.setId(UUID.randomUUID().toString());

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(realm));

        eventBusService.publishEvent(event);

    }

    @DeleteMapping("/delete")
    public void deleteRealm(@RequestParam String id){

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
