package com.example.fashionday.data.repository

import com.example.fashionday.data.network.api.RickAndMortyApi
import com.example.fashionday.data.network.model.CharacterResponse
import com.example.fashionday.domain.usecase.Character
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
            while (!isLastPage) {
                val response = endpoints.getCharacters(page)
                allResponses.add(response)

                // Determine if this is the last page
                isLastPage = response.info?.next == null
                page++
            }
        }

        Result.success(allResponses.toCharacters())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}


fun List<CharacterResponse>.toCharacters(): List<Character> {
    return this
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
                status = resultsItem.status
            )
        }
}


