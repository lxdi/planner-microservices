package com.sogoodlabs.planner.targets.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogoodlabs.planner.data.model.Target;
import com.sogoodlabs.planner.targets.service.service.TargetsCudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class.getName());

    @Autowired
    private TargetsCudService targetsCudService;

    @GetMapping
    public String heartbeat(){
        return "heartbeat";
    }

    @GetMapping("/version")
    public String version(){
        log.info("Getting version ");
        return "0.0.1";
    }

    @PostMapping("/targets/create")
    public void createTarget(@RequestBody Target target) throws JsonProcessingException {
        targetsCudService.createTarget(target);
    }

    @DeleteMapping("/targets/delete")
    public void deleteTarget(@RequestParam String id){
        targetsCudService.deleteTarget(id);
    }


}
