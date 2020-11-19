package com.sogoodlabs.planner.dataaccess.controller;

import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.planner.dataaccess.data.LayersRepository;
import com.sogoodlabs.planner.dataaccess.data.MeansRepository;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import com.sogoodlabs.planner.dataaccess.data.TargetsRepository;
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
    private TargetsRepository targetsRepository;

    @Autowired
    private MeansRepository meansRepository;

    @Autowired
    private LayersRepository layersRepository;

    @Autowired
    private CommonMapper commonMapper;

    @GetMapping("/realms/get/all")
    public List<Map<String, Object>> getRealms(){
        List<Map<String, Object>> result = new ArrayList<>();
        realmsRepository.findAll().forEach(realm -> {
            result.add(commonMapper.mapToDto(realm));
        });
        return result;
    }

    @GetMapping("/realms/get")
    public Map<String, Object> getRealmById(@RequestParam("id") String id){
        return commonMapper.mapToDto(realmsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Realm not found by id: " + id)));
    }

    @GetMapping("/targets/get/all")
    public List<Map<String, Object>> getTargetsAll(){
        List<Map<String, Object>> result = new ArrayList<>();
        targetsRepository.findAll().forEach(realm -> {
            result.add(commonMapper.mapToDto(realm));
        });
        return result;
    }

    @GetMapping("/targets/get")
    public Map<String, Object> getTargetById(@RequestParam("id") String id){
        return commonMapper.mapToDto(targetsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Target not found by id: " + id)));
    }


    @GetMapping("/means/get/all")
    public List<Map<String, Object>> getMeansAll(){
        List<Map<String, Object>> result = new ArrayList<>();
        meansRepository.findAll().forEach(realm -> {
            result.add(commonMapper.mapToDto(realm));
        });
        return result;
    }

    @GetMapping("/means/get")
    public Map<String, Object> getMeansById(@RequestParam("id") String id){
        return commonMapper.mapToDto(meansRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Layer not found by id: " + id)));
    }

    @GetMapping("/layers/get/all")
    public List<Map<String, Object>> getLayersAll(){
        List<Map<String, Object>> result = new ArrayList<>();
        layersRepository.findAll().forEach(realm -> {
            result.add(commonMapper.mapToDto(realm));
        });
        return result;
    }

    @GetMapping("/layers/get")
    public Map<String, Object> getLayerById(@RequestParam("id") String id){
        return commonMapper.mapToDto(layersRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Layer not found by id: " + id)));
    }

}
