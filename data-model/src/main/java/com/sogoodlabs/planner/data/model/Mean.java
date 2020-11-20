package com.sogoodlabs.planner.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Mean implements Serializable {

    @Id
    private String id;

    private String title;

    private String realmid;

    @Transient
    private List<Layer> layers;

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

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
