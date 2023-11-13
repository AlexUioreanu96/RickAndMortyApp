package com.example.fashionday.data.network.api

import com.example.fashionday.data.network.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Int): CharacterResponse

}