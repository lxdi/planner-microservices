package com.sogoodlabs.planner.controllers;

import com.sogoodlabs.planner.controllers.clients.RealmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    @Value("${services.realms.url}")
    String realmsUrl;

    @Value("${service.name}")
    String serviceName;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RealmsClient realmsClient;

    @GetMapping
    public String heartbeat(){
        return "heartbeat";
    }

    @GetMapping("/version")
    public String version(){
        LOG.info("Getting version");
        return serviceName + "0.0.1";
    }

    @GetMapping("/realms/version")
    public String realmVersion(){
        LOG.info("Getting version for realms");
        ResponseEntity<String> response = restTemplate.getForEntity(realmsUrl+"/version", String.class);
        return response.getBody();
    }

    @Deprecated
    @GetMapping("/realms/version/feign")
    public String serviceVersionFeign(){
        LOG.info("Getting version for realms using feign");
        return realmsClient.version();
    }

    @Deprecated
    @GetMapping("/service/version")
    public String serviceVersion(@RequestParam("url") String url){
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://"+url+"/version", String.class);
        return response.getBody();
    }

    @Deprecated
    @GetMapping("/check/connection")
    public String checkConnection(@RequestParam("url") String url){
        return restTemplate.getForEntity(url, String.class).getStatusCode().toString();
    }

}
