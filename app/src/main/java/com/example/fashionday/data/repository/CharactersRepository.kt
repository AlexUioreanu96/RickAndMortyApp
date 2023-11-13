package com.example.fashionday.data.repository

import com.example.fashionday.domain.usecase.Character

interface CharactersRepository {

    suspend fun getCharacters(): Result<List<Character>>

}