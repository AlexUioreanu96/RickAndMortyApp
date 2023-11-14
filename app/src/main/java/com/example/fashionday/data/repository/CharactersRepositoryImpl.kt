package com.example.fashionday.data.repository

import com.example.fashionday.data.network.api.RickAndMortyApi
import com.example.fashionday.data.network.model.CharacterResponse
import com.example.fashionday.data.network.model.ResultsItem
import com.example.fashionday.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val endpoints: RickAndMortyApi
) : CharactersRepository {
    override suspend fun getCharacters(): Result<List<Character>> = try {
        val allResponses = mutableListOf<CharacterResponse>()
        var page = 1
        var isLastPage = false

        withContext(Dispatchers.IO) {
            while (page < 20) {
                val response = endpoints.getCharacters(page)
                allResponses.add(response)

                isLastPage = response.info?.next == null
                page++
            }
        }

        Result.success(allResponses.toCharacters())
    } catch (e: Throwable) {
        Result.failure(e)
    }

    override suspend fun getCharacter(id: Int): Result<Character> =
        try {
            val result = endpoints.getCharacterDetails(id)
            Result.success(result.toCharacter())
        } catch (e: Throwable) {
            Result.failure(e)
        }
}

fun ResultsItem.toCharacter(): Character =
    Character(
        id = id,
        image = image,
        gender = gender,
        species = species,
        name = name,
        type = type,
        status = status,
        origin = origin?.name
    )


fun List<CharacterResponse>.toCharacters(): List<Character> =
    this
        .mapNotNull { it.results }
        .flatten()
        .filterNotNull()
        .map { resultsItem ->
            Character(
                id = resultsItem.id,
                image = resultsItem.image,
                gender = resultsItem.gender,
                species = resultsItem.species,
                name = resultsItem.name,
                type = resultsItem.type,
                status = resultsItem.status,
                origin = resultsItem.origin?.name
            )
        }



