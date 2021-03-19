package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="relation")
public class Relation {


    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="owner")
    private User owner;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="buddy")
    private User buddy;

    // constructors

    public Relation() {
    }

    public Relation(Integer id) {
        this.id = id;
    }

// getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBuddy() {
        return buddy;
    }

    public void setBuddy(User buddy) {
        this.buddy = buddy;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", owner=" + owner +
                ", buddy=" + buddy +
                '}';
    }
}
