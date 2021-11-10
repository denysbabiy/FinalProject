package com.epam.finalproject.db.entity;

import java.io.Serializable;

public class Entity implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
