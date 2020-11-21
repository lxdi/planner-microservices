package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.MeanTargetRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MeanTargetRelationRepository extends CrudRepository<MeanTargetRelation, String> {

    public List<MeanTargetRelation> findByMeanid(String meanid);

}
