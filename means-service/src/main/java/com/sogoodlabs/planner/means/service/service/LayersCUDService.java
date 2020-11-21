package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.Layer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class LayersCUDService {

    private static Logger log = Logger.getLogger(LayersCUDService.class.getName());

    @Autowired
    private TasksCUDService tasksCUDService;

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private IMapper mapper;

    public void createLayer(Layer layer, String meanId) {

        log.info("Creating layer " + layer.getNum() + " for mean " + meanId);

        layer.setId(UUID.randomUUID().toString());
        layer.setMeanid(meanId);

        Event event = new Event();
        event.setEventType(EventType.CREATE);
        event.setPayload(mapper.writeValueAsString(layer));

        eventBusService.publishLayerEvent(event);


        if(layer.getTasks()!=null && !layer.getTasks().isEmpty()){
            layer.getTasks().forEach(task -> tasksCUDService.createTask(task, layer.getId()));
        }

    }

}
