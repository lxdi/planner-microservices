package com.sogoodlabs.planner.dataaccess.controller;

import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.data.model.Target;
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

    @GetMapping("/realms/get/all")
    public List<Realm> getRealms(){
        List<Realm> result = new ArrayList<>();
        realmsRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/realms/get")
    public Realm getRealmById(@RequestParam("id") String id){
        return realmsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Realm not found by id: " + id));
    }

    @GetMapping("/targets/get/all")
    public List<Target> getTargetsAll(){
        List<Target> result = new ArrayList<>();
        targetsRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/targets/get")
    public Target getTargetById(@RequestParam("id") String id){
        return targetsRepository.findById(id).orElseThrow(() -> new NullPointerException("Target not found by id: " + id));
    }


    @GetMapping("/means/get/all")
    public List<Mean> getMeansAll(){
        List<Mean> result = new ArrayList<>();
        meansRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/means/get")
    public Mean getMeansById(@RequestParam("id") String id){
        return meansRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Layer not found by id: " + id));
    }

    @GetMapping("/layers/get/all")
    public List<Layer> getLayersAll(){
        List<Layer> result = new ArrayList<>();
        layersRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/layers/get")
    public Layer getLayerById(@RequestParam("id") String id){
        return layersRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Layer not found by id: " + id));
    }

}
