package com.sogoodlabs.planner.means.service.client;

import com.sogoodlabs.planner.data.model.Mean;
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

    @RequestMapping(value = "/targets/get", method = RequestMethod.GET)
    public Map<String, Object> getTargetById(@RequestParam("id") String id);

    @RequestMapping(value = "/means/get", method = RequestMethod.GET)
    public Mean getMeanById(@RequestParam("id") String id);

}
