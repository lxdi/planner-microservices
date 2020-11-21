package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.Realm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeansRepository extends CrudRepository<Mean, String> {

    List<Mean> findByRealmid(String realmid);

}
