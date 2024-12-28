package com.example.musicapp.model;


import java.io.Serializable;

public class Music implements Serializable {
    private String title;
    private String artist;
    private String url;

    public Music() {
        // Constructor rỗng để Firebase sử dụng
    }

    public Music(String title, String artist, String url) {
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }
}