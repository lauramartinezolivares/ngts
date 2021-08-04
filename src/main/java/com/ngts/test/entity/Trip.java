package com.ngts.test.entity;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Trip implements Serializable {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column
    private int cost;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
