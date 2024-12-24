package com.example.musicapp.model;

public class Playlist {
    private String id;
    private String name;
    private String createdDate;

    public Playlist() {}

    public Playlist(String id, String name, String createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}

