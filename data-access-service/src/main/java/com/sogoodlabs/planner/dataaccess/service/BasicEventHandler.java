package com.sogoodlabs.planner.dataaccess.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.*;
import com.sogoodlabs.planner.dataaccess.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicEventHandler {

    private static Logger log = LoggerFactory.getLogger(BasicEventHandler.class.getName());

    @Autowired
    private RealmsRepository realmsRepository;

    @Autowired
    private TargetsRepository targetsRepository;

    @Autowired
    private MeansRepository meansRepository;

    @Autowired
    private LayersRepository layersRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private MeanTargetRelationRepository meanTargetRelationRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public void handleRealmsEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            Realm realm = mapper.readValue(event.getPayload(), Realm.class);
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

    public void handleTargetsEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            Target target = mapper.readValue(event.getPayload(), Target.class);
            log.info("Creating target: " + target.getTitle() + ", id: " + target.getId());
            targetsRepository.save(target);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting target by id: " + event.getPayload());
            targetsRepository.deleteById((String) event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

    public void handleMeansEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            Mean mean = mapper.readValue(event.getPayload(), Mean.class);
            log.info("Creating mean: " + mean.getTitle() + ", id: " + mean.getId());

            if(mean.getLayers()!=null && mean.getLayers().size()>0){
                mean.getLayers().forEach(layer -> {
                    if(layer.getTasks()!=null && layer.getTasks().size()>0){
                        layer.getTasks().forEach(task -> {
                            tasksRepository.save(task);
                        });
                    }
                    layersRepository.save(layer);
                });
            }

            meansRepository.save(mean);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting mean by id: " + event.getPayload());
            meansRepository.deleteById((String) event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

    public void handleTargetMeanRelationsEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            MeanTargetRelation meanTargetRelation = mapper.readValue(event.getPayload(), MeanTargetRelation.class);
            log.info("Creating target-mean relation: target " + meanTargetRelation.getTargetid() + ", mean : " + meanTargetRelation.getMeanid());
            meanTargetRelationRepository.save(meanTargetRelation);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting target-mean relation by id: " + event.getPayload());
            meanTargetRelationRepository.deleteById(event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

}
