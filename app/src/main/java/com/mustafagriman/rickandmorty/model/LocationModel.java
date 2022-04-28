package com.mustafagriman.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

public class LocationModel
{
    @SerializedName("name")
    public String locationName;

    @SerializedName("url")
    public String locationUrl;
}
