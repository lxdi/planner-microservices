package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.Task;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class TasksCUDService {

    private static Logger log = LoggerFactory.getLogger(TasksCUDService.class.getName());

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private IMapper mapper;

    public void createTask(Task task, String layerid) {

        log.info("Creating task {} for layer {}", task.getTitle(), layerid);

        task.setId(UUID.randomUUID().toString());
        task.setLayerid(layerid);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(task));

        eventBusService.publishTaskEvent(event);
    }

    public void deleteTask(@RequestParam String id){
        log.info("Deleting task with id " + id);

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishTaskEvent(event);
    }

    public void deleteTasksByLayer(String layerid){
        dataAccessClient.getTasksByLayerId(layerid)
                .forEach(task -> deleteTask(task.getId()));
    }

}
