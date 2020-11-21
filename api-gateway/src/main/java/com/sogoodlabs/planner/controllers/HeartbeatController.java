package com.sogoodlabs.planner.controllers;

import com.sogoodlabs.planner.dto.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class HeartbeatController {

    private final static String HEARTBEAT_URL = "/actuator/health";

    @Value("${zuul.routes.realms.url}")
    private String realmsUrl;

    @Value("${zuul.routes.targets.url}")
    private String targetsUrl;

    @Value("${zuul.routes.means.url}")
    private String meansUrl;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/services/heartbeat")
    public List<ServiceStatus> heartbeat(){
        return Arrays.asList(
                checkService(realmsUrl, "realms-service"),
                checkService(targetsUrl, "targets-service"),
                checkService(meansUrl, "means-service")
                );
    }


    private ServiceStatus checkService(String url, String name){
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setName(name);
        serviceStatus.setUrl(url);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url+HEARTBEAT_URL, String.class);
            serviceStatus.setStatus(""+response.getStatusCodeValue());
        } catch (Exception ex){
            serviceStatus.setStatus("Error: "+ex.getMessage());
        }
        return serviceStatus;
    }

}
