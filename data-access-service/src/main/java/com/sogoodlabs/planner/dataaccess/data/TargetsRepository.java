package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.data.model.Target;
import org.springframework.data.repository.CrudRepository;

public interface TargetsRepository extends CrudRepository<Target, String> {
}
