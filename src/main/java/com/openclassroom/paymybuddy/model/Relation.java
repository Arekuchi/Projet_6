package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="relation")
public class Relation {

    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="owner")
    private int owner;
    @Column(name="buddy")
    private int buddy;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    // constructors


    public Relation() {
    }

    public Relation(int id, int owner, int buddy) {
        this.id = id;
        this.owner = owner;
        this.buddy = buddy;
    }

    public Relation(int id, int owner, int buddy, User user) {
        this.id = id;
        this.owner = owner;
        this.buddy = buddy;
        this.user = user;
    }

    // getters & setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getBuddy() {
        return buddy;
    }

    public void setBuddy(int buddy) {
        this.buddy = buddy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
