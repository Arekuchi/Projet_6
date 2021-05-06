package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // constructors


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
