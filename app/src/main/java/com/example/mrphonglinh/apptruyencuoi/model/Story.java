package com.example.mrphonglinh.apptruyencuoi.model;

import java.io.Serializable;

/**
 * Created by MyPC on 14/01/2017.
 */

public class Story implements Serializable{
    private int id;
    private String name;
    private String content;
    private int catId;
    private String created;

    public Story(int id, String name, String content, int catId, String created) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.catId = catId;
        this.created = created;
    }

    public Story() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
