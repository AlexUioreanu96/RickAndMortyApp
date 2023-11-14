package com.example.fashionday.domain.usecase

import com.example.fashionday.data.repository.CharactersRepository
import com.example.fashionday.domain.model.Character
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(id: Int): Result<Character> = repository.getCharacter(id)
}