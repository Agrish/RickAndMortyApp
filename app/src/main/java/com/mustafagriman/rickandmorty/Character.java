package com.mustafagriman.rickandmorty;

public class Character {
    private String name;
    private String status;
    private String imageUrl;

    private String lastKnownLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(String lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public Character(String name, String status, String imageUrl, String lastKnownLocation) {
        this.name = name;
        this.status = status;
        this.imageUrl = imageUrl;
        this.lastKnownLocation = lastKnownLocation;
    }





}
