package com.sogoodlabs.planner.means.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import com.sogoodlabs.planner.means.service.service.EventBusService;
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

    @PostMapping("/means/create")
    public void createMean(@RequestBody Mean mean) throws JsonProcessingException {

        checkRealm(mean.getRealmid());

        log.info("Creating mean with title " + mean.getTitle());

        String meanId = UUID.randomUUID().toString();
        mean.setId(meanId);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(mean));

        eventBusService.publishMeanEvent(event);

        if(mean.getLayers()!=null && !mean.getLayers().isEmpty()){
            createLayers(mean.getLayers(), meanId);
        }

    }

    private void checkRealm(String realmId){
        if(realmId==null){
            throw new RuntimeException("Mean should have a realm");
        }

        if(dataAccessClient.getRealmById(realmId)==null){
            throw new RuntimeException("Error while creating mean - realm with id "+ realmId + " doesn't exist");
        }
    }

    private void createLayers(List<Layer> layersList, String meanId) throws JsonProcessingException {

        log.info("Creating layers for mean: " + meanId);

        for(Layer layer : layersList){
            layer.setId(UUID.randomUUID().toString());
            layer.setMeanid(meanId);

            Event event = new Event();
            event.setEventType(EventType.CREATE);
            event.setPayload(mapper.writeValueAsString(layer));

            eventBusService.publishLayerEvent(event);
        }

    }


    @DeleteMapping("/means/delete")
    public void deleteRealm(@RequestParam String id){

        log.info("Deleting realm with id " + id);

        if(dataAccessClient.getRealmById(id)==null){
            throw new RuntimeException("Realm with id " + id + " doesn't exist");
        }

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishMeanEvent(event);
    }


}
