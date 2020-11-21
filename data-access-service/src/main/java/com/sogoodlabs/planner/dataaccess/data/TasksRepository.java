package com.sogoodlabs.planner.dataaccess.data;

import com.sogoodlabs.planner.data.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepository extends CrudRepository<Task, String> {
}
