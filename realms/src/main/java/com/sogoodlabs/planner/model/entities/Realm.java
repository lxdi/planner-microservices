package com.sogoodlabs.planner.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Realm {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String title;

    public Realm(){};

    public Realm(String title){
        this.title = title;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
