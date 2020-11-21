package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class TasksCUDService {

    private static Logger log = Logger.getLogger(TasksCUDService.class.getName());

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private IMapper mapper;

    public void createTask(Task task, String layerid) {

        log.info("Creating task " + task.getTitle() + " for layer " + layerid);

        task.setId(UUID.randomUUID().toString());
        task.setLayerid(layerid);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(task));

        eventBusService.publishTaskEvent(event);
    }

}
