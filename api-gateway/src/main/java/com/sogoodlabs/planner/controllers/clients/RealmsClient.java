package com.sogoodlabs.planner.controllers.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "realms-service", url = "${services.realms.url}")
public interface RealmsClient {

    @GetMapping("/version")
    public String version();

}
