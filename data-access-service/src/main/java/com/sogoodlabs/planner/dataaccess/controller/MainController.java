package com.sogoodlabs.planner.dataaccess.controller;

import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private RealmsRepository realmsRepository;

    @Autowired
    private CommonMapper commonMapper;

    @GetMapping("/realms/get/all")
    public List<Realm> getRealms(){
        List<Realm> result = new ArrayList<>();
        realmsRepository.findAll().forEach(result::add);
        return result;
    }

    @GetMapping("/realms/get")
    public Map<String, Object> getRealmById(@RequestParam("id") String id){
        return commonMapper.mapToDto(realmsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Realm not found by id: " + id)));
    }


}
