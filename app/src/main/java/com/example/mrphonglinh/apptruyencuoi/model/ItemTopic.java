package com.example.mrphonglinh.apptruyencuoi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MyPC on 14/01/2017.
 */

public class ItemTopic implements Serializable {
    private int image;
    private String topic;
    private ArrayList<ItemStory> stories;

    public ItemTopic(int image, String topic, ArrayList<ItemStory> stories) {
        this.image = image;
        this.topic = topic;
        this.stories = stories;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<ItemStory> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ItemStory> stories) {
        this.stories = stories;
    }
}
