package com.sogoodlabs.planner.realms.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "data-access-service", url = "${services.data-access-service.url}")
public interface DataAccessClient {

    @RequestMapping(value = "/realms/get", method = RequestMethod.GET)
    public Map<String, Object> getRealmById(@RequestParam("id") String id);

}
