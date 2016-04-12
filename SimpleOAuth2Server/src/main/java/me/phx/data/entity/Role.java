package me.phx.data.entity;

import javax.persistence.*;

/**
 * @author ye
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}