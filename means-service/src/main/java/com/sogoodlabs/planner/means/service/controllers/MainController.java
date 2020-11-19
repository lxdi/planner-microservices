package com.sogoodlabs.planner.means.service.controllers;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import com.sogoodlabs.planner.means.service.service.EventBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
public class MainController {

    private static Logger log = Logger.getLogger(MainController.class.getName());

    private static final String REALM_ID = "realmid";
    private static final String MEAN_ID = "meanid";

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

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
    public void createRealm(@RequestBody HashMap<String, Object> meanDto){

        checkRealm((String) meanDto.get(REALM_ID));

        log.info("Creating mean with title " + meanDto.get("title"));

        String meanId = UUID.randomUUID().toString();
        meanDto.put("id", meanId);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(meanDto);

        eventBusService.publishMeanEvent(event);

        if(meanDto.get("layers")!=null){
            createLayers((List<HashMap<String, Object>>) meanDto.get("layers"), meanId);
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

    private void createLayers(List<HashMap<String, Object>> layersArrList, String meanId){

        log.info("Creating layers for mean: " + meanId);

        for(HashMap<String, Object> layerDto : layersArrList){
            layerDto.put("id", UUID.randomUUID().toString());
            layerDto.put(MEAN_ID, meanId);

            Event event = new Event();
            event.setEventType(EventType.CREATE);
            event.setPayload(layerDto);

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
