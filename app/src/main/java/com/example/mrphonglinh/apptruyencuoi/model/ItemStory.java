package com.example.mrphonglinh.apptruyencuoi.model;

import java.io.Serializable;

/**
 * Created by MyPC on 14/01/2017.
 */

public class ItemStory implements Serializable{
    private String title;
    private String content;

    public ItemStory(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
