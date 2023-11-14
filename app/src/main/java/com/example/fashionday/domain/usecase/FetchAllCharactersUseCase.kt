package com.example.fashionday.domain.usecase

import com.example.fashionday.data.repository.CharactersRepository
import com.example.fashionday.domain.model.Character
import javax.inject.Inject

class FetchAllCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(): Result<List<Character>> = repository.getCharacters()
}