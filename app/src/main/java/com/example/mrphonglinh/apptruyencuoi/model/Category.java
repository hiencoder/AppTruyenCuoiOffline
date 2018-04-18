package com.example.mrphonglinh.apptruyencuoi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MyPC on 14/01/2017.
 */

public class Category implements Serializable {
    private int id;
    private String name;
    private int count;
    private int ordering;

    public Category(int id, String name, int count, int ordering) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.ordering = ordering;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
