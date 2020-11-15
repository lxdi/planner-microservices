package com.sogoodlabs.planner.data.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Target {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Realm realm;

    private String title;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Realm getRealm() {
        return realm;
    }
    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
