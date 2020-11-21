package com.sogoodlabs.planner.means.service.client;

import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.Task;
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

    @RequestMapping(value = "/means/get", method = RequestMethod.GET)
    Mean getMeanById(@RequestParam("id") String id);

    @RequestMapping(value = "/means/get/by/realm", method = RequestMethod.GET)
    List<Mean> getMeansByRealmid(@RequestParam("id") String realmid);

    @RequestMapping(value = "/layers/get/by/mean", method = RequestMethod.GET)
    List<Layer> getLayersByMeanid(@RequestParam("id") String meanid);

    @RequestMapping(value = "/tasks/get/by/layer", method = RequestMethod.GET)
    List<Task> getTasksByLayerId(@RequestParam("id") String layerid);

}
