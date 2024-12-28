package com.example.musicapp.model;

import java.util.List;

public class Playlist {
    private String id;
    private String name;
    private String created_at;
    private List<Music> musicList;


    public Playlist() {}

    public Playlist(String id, String name, String created_at, List<Music> musicList) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.musicList = musicList;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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



}

