package com.sogoodlabs.planner.means.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.MeanTargetRelation;
import com.sogoodlabs.planner.means.service.service.MeanTargetAssignService;
import com.sogoodlabs.planner.means.service.service.MeansCUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class MainController {

    private static Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    private MeansCUDService meansCUDService;

    @Autowired
    private MeanTargetAssignService meanTargetAssignService;

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
    public void createMean(@RequestBody Mean mean) throws JsonProcessingException {
        meansCUDService.createMean(mean);
    }

    @DeleteMapping("/means/delete")
    public void deleteMean(@RequestParam String id){
        meansCUDService.deleteMean(id);
    }

    @PostMapping("/means/target/relation/create")
    public void createTargetMeanRelation(@RequestBody MeanTargetRelation meanTargetRelation){
        meanTargetAssignService.assignToTarget(meanTargetRelation);
    }

    

}
