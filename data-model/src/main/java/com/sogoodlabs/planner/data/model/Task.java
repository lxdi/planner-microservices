package com.sogoodlabs.planner.data.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @Id
    private String id;

    private String title;

    private String layerid;

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

    public String getLayerid() {
        return layerid;
    }

    public void setLayerid(String layerid) {
        this.layerid = layerid;
    }
}
