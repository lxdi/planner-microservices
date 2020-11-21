package com.sogoodlabs.planner.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MeanTargetRelation {

    @Id
    private String id;

    private String meanid;
    private String targetid;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMeanid() {
        return meanid;
    }
    public void setMeanid(String meanid) {
        this.meanid = meanid;
    }

    public String getTargetid() {
        return targetid;
    }
    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }
}
