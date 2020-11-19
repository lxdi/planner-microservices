package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Layer;
import com.sogoodlabs.planner.data.model.Mean;
import org.springframework.data.repository.CrudRepository;

public interface LayersRepository extends CrudRepository<Layer, String> {
}
