package com.sogoodlabs.planner.dataaccess.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.*;
import com.sogoodlabs.planner.dataaccess.data.*;
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
    private TargetsRepository targetsRepository;

    @Autowired
    private MeansRepository meansRepository;

    @Autowired
    private LayersRepository layersRepository;

    @Autowired
    private TasksRepository tasksRepository;

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

    public void handleLayersEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            Layer layer = mapper.readValue(event.getPayload(), Layer.class);
            log.info("Creating layer: " + layer.getNum() + ", id: " + layer.getId());
            layersRepository.save(layer);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting layer by id: " + event.getPayload());
            layersRepository.deleteById((String) event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

    public void handleTasksEvent(Event event) throws JsonProcessingException {
        if(event.getEventType() == EventType.CREATE){
            Task task = mapper.readValue(event.getPayload(), Task.class);
            log.info("Creating task: " + task.getTitle() + ", id: " + task.getId());
            tasksRepository.save(task);
            return;
        }

        if(event.getEventType() == EventType.DELETE){
            log.info("Deleting task by id: " + event.getPayload());
            tasksRepository.deleteById(event.getPayload());
            return;
        }

        log.info("Unknown type of event; skipping");
    }

}
