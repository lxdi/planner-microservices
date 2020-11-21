package com.sogoodlabs.planner.means.service.service;


import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class MeansCUDService {

    private static Logger log = LoggerFactory.getLogger(MeansCUDService.class.getName());

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private LayersCUDService layersCUDService;

    @Autowired
    private IMapper mapper;


    public void createMean(Mean mean) {

        checkRealm(mean.getRealmid());

        log.info("Creating mean with title " + mean.getTitle());

        String meanId = UUID.randomUUID().toString();
        mean.setId(meanId);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(mean));

        eventBusService.publishMeanEvent(event);

        if(mean.getLayers()!=null && !mean.getLayers().isEmpty()){
            log.info("Creating layers for mean: " + meanId);
            mean.getLayers().forEach(layer -> layersCUDService.createLayer(layer, meanId));
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


    public void deleteMean(@RequestParam String id){
        log.info("Deleting mean with id " + id);

        layersCUDService.deleteLayersByMean(id);

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishMeanEvent(event);
    }


    public void deleteMeansForRealm(String realmid){
        dataAccessClient.getMeansByRealmid(realmid)
                .forEach(mean -> deleteMean(mean.getId()));
    }

}
