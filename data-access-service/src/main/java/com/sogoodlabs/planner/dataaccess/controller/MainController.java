package com.sogoodlabs.planner.dataaccess.controller;

import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private RealmsRepository realmsRepository;

    @GetMapping("/realms/get/all")
    public List<Realm> getRealms(){
        List<Realm> result = new ArrayList<>();
        realmsRepository.findAll().forEach(result::add);
        return result;
    }


}
