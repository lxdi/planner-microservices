package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.Realm;
import org.springframework.data.repository.CrudRepository;

public interface MeansRepository extends CrudRepository<Mean, String> {
}
