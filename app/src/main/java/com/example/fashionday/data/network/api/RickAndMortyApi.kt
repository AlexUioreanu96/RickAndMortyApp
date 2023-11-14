package com.example.fashionday.data.network.api

import com.example.fashionday.data.network.model.CharacterResponse
import com.example.fashionday.data.network.model.ResultsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): ResultsItem

}