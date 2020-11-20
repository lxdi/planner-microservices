package com.sogoodlabs.planner.data.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Target implements Serializable {

    @Id
    private String id;

    private String realmid;

    private String title;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getRealmid() {
        return realmid;
    }
    public void setRealmid(String realmid) {
        this.realmid = realmid;
    }
}
