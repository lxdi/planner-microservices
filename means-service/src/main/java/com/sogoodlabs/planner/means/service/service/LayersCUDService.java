package com.sogoodlabs.planner.means.service.service;

import com.sogoodlabs.planner.data.common.events.Event;
import com.sogoodlabs.planner.data.common.events.EventType;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.means.service.client.DataAccessClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class LayersCUDService {

    private static Logger log = LoggerFactory.getLogger(LayersCUDService.class.getName());

    @Autowired
    private TasksCUDService tasksCUDService;

    @Autowired
    private DataAccessClient dataAccessClient;

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private IMapper mapper;

    public void createLayer(Layer layer, String meanId) {

        log.info("Creating layer {}, for mean {}", layer.getNum(), meanId);

        layer.setId(UUID.randomUUID().toString());
        layer.setMeanid(meanId);

//        Event event = new Event();
//        event.setEventType(EventType.CREATE);
//        event.setPayload(mapper.writeValueAsString(layer));
//
//        eventBusService.publishLayerEvent(event);


        if(layer.getTasks()!=null && !layer.getTasks().isEmpty()){
            layer.getTasks().forEach(task -> tasksCUDService.createTask(task, layer.getId()));
        }

    }

    public void deleteLayer(@RequestParam String id){
        log.info("Deleting layer with id " + id);

        tasksCUDService.deleteTasksByLayer(id);

        Event event = new Event();
        event.setEventType(EventType.DELETE);
        event.setPayload(id);

        eventBusService.publishLayerEvent(event);
    }

    public void deleteLayersByMean(String meanid){
        dataAccessClient.getLayersByMeanid(meanid)
                .forEach(layer -> deleteLayer(layer.getId()));
    }

}
