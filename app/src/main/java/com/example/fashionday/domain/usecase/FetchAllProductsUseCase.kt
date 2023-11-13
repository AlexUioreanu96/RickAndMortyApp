package com.example.fashionday.domain.usecase

import com.example.fashionday.data.network.model.CharacterResponse
import com.example.fashionday.data.repository.CharactersRepository
import javax.inject.Inject

class FetchAllProductsUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(): Result<List<Character>> = repository.getCharacters()
}