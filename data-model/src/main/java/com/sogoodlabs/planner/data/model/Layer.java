package com.sogoodlabs.planner.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Layer implements Serializable {

    @Id
    private String id;

    private Integer num;

    private String meanid;

    @Transient
    private List<Task> tasks;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMeanid() {
        return meanid;
    }
    public void setMeanid(String meanid) {
        this.meanid = meanid;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
