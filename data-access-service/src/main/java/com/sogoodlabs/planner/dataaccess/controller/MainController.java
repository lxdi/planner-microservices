package com.sogoodlabs.planner.dataaccess.controller;

import com.sogoodlabs.planner.data.model.*;
import com.sogoodlabs.planner.dataaccess.data.*;
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
    private TasksRepository tasksRepository;

    @Autowired
    private MeanTargetRelationRepository meanTargetRelationRepository;

    @GetMapping("/version")
    public String version(){
        return "0.0.1";
    }

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

    @GetMapping("/targets/get/by/realm")
    public List<Target> getTargetsByRealm(@RequestParam("id") String realmid){
        return targetsRepository.findByRealmid(realmid);
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

    @GetMapping("/means/get/target/relations")
    public List<MeanTargetRelation> getMeanTargetRelationById(@RequestParam("id") String meanid){
        List<MeanTargetRelation> result = new ArrayList<>();
        meanTargetRelationRepository.findByMeanid(meanid).iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/means/get/by/realm")
    public List<Mean> getMeansByRealmId(@RequestParam("id") String realmid){
        return meansRepository.findByRealmid(realmid);
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

    @GetMapping("/layers/get/by/mean")
    public List<Layer> getMeansByMeanId(@RequestParam("id") String meanid){
        return layersRepository.findByMeanid(meanid);
    }

    @GetMapping("/tasks/get/all")
    public List<Task> getTasksAll(){
        List<Task> result = new ArrayList<>();
        tasksRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @GetMapping("/tasks/get")
    public Task getTaskById(@RequestParam("id") String id){
        return tasksRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Task not found by id: " + id));
    }

    @GetMapping("/tasks/get/by/layer")
    public List<Task> getTaskByMeanId(@RequestParam("id") String layerid){
        return tasksRepository.findByLayerid(layerid);
    }

}
