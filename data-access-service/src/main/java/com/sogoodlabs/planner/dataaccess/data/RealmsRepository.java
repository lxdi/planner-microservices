package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Realm;
import org.springframework.data.repository.CrudRepository;

public interface RealmsRepository extends CrudRepository<Realm, Long> {
}
