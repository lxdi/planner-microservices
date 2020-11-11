package com.sogoodlabs.planner.controllers;

import com.sogoodlabs.planner.model.dao.RealmsRepository;
import com.sogoodlabs.planner.model.entities.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class MainController {

    @Autowired
    RealmsRepository realmsRepository;

    private static Logger LOG = Logger.getLogger(MainController.class.getName());

    @GetMapping
    public String heartbeat(){
        return "heartbeat";
    }

    @GetMapping("/version")
    public String version(){
        LOG.info("Getting version ");
        return "0.0.1";
    }

    @PutMapping("/create")
    public String createRealm(@RequestBody Map<String, String> realmData){
        realmsRepository.save(new Realm(realmData.get("title")));
        return HttpStatus.OK.name();
    }

    @GetMapping("/get/all")
    public List<Realm> getAll(){
        List<Realm> realms = new ArrayList<>();
        realmsRepository.findAll().forEach(realms::add);
        return realms;
    }


}
