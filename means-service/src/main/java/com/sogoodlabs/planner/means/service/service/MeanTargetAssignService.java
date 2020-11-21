package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.MeanTargetRelation;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MeanTargetAssignService {

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private IMapper mapper;

    public void assignToTarget(MeanTargetRelation meanTargetRelation){

        if(dataAccessClient.getMeanById(meanTargetRelation.getMeanid())==null){
            throw new RuntimeException("Mean doesn't exist " + meanTargetRelation.getMeanid());
        }

        if(dataAccessClient.getTargetById(meanTargetRelation.getTargetid())==null){
            throw new RuntimeException("Target doesn't exist " + meanTargetRelation.getTargetid());
        }

        meanTargetRelation.setId(UUID.randomUUID().toString());

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(meanTargetRelation));

        eventBusService.publishTargetMeanRelationEvent(event);

    }

}
