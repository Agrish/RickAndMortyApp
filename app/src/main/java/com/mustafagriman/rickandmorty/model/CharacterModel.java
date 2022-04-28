package com.mustafagriman.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

public class CharacterModel {
    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

    @SerializedName("image")
    public String imageUrl;

    @SerializedName("location")
    public LocationModel lastKnownLocation;

}
