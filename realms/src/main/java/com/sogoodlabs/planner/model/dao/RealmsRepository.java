package com.sogoodlabs.planner.model.dao;

import com.sogoodlabs.planner.model.entities.Realm;
import org.springframework.data.repository.CrudRepository;

public interface RealmsRepository extends CrudRepository<Realm, Long> {
}
