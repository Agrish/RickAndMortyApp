package com.mustafagriman.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterPageResponseModel
{
    @SerializedName("info")
    public PageInformation info;

    @SerializedName("results")
    public List<CharacterModel> characterModels;
}
