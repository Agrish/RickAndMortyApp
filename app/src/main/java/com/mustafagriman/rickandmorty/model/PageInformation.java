package com.mustafagriman.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

public class PageInformation
{
    @SerializedName("count")
    public int count;

    @SerializedName("pages")
    public int pages;

    @SerializedName("next")
    public String next;

    @SerializedName("prev")
    public String prev;
}
