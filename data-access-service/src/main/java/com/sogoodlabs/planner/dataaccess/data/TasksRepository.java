package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepository extends CrudRepository<Task, String> {

    List<Task> findByLayerid(String id);

}
