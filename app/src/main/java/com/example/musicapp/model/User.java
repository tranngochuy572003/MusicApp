package com.example.musicapp.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String uuid   ;
    private String username;
    private String email;
    private String dob;
    private String phone;
    private String password;
    private String gender;
    List<Playlist> playlistList;
    List<Music>  favouriteSong;

    public User() {
    }

    public User(String uuid, String username, String email, String dob, String phone, String password, String gender, List<Playlist> playlistList, List<Music> favouriteSong) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.playlistList = playlistList;
        this.favouriteSong = favouriteSong;
    }

    public List<Music> getFavouriteSong() {
        return favouriteSong;
    }

    public void setFavouriteSong(List<Music> favouriteSong) {
        this.favouriteSong = favouriteSong;
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    public void setPlaylistList(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return uuid ;
    }

    public void setId(String id) {
        this.uuid  = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

