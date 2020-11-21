package com.sogoodlabs.planner.targets.service.client;

import com.sogoodlabs.planner.data.model.Target;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "data-access-service", url = "${services.data-access-service.url}")
public interface DataAccessClient {

    @RequestMapping(value = "/realms/get", method = RequestMethod.GET)
    Map<String, Object> getRealmById(@RequestParam("id") String id);

    @RequestMapping(value = "/targets/get", method = RequestMethod.GET)
    Map<String, Object> getTargetById(@RequestParam("id") String id);

    @RequestMapping(value = "/targets/get/by/realm", method = RequestMethod.GET)
    List<Target> getTargetsByRealmid(@RequestParam("id") String id);

}
