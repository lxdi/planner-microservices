package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Mean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LayersRepository extends CrudRepository<Layer, String> {

    List<Layer> findByMeanid(String meanid);

}
