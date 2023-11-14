package com.example.fashionday.data.repository

import com.example.fashionday.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(): Result<List<Character>>
    suspend fun getCharacter(id: Int): Result<Character>

}