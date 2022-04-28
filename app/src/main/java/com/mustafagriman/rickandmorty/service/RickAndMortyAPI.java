package com.mustafagriman.rickandmorty.service;

import com.mustafagriman.rickandmorty.model.CharacterModel;
import com.mustafagriman.rickandmorty.model.CharacterPageResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RickAndMortyAPI
{
    @GET("character/{character_id}")
    Call<CharacterModel> getCharacterById(@Path("character_id") int id);

    @GET("character")
    Call<CharacterPageResponseModel> getCharacterPageResponse(@Query("page") int pageIndex);
}
