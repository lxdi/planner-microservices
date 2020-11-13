package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.common.data.entities.Realm;
import org.springframework.data.repository.CrudRepository;

public interface RealmsRepository extends CrudRepository<Realm, Long> {
}
