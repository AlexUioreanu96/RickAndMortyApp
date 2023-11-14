package com.example.fashionday.ui.screen.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashionday.domain.model.Character
import com.example.fashionday.domain.usecase.FetchAllCharactersUseCase
import com.example.fashionday.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterUiState(
    val searchQuery: String? = null,
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val filteredCharacters: List<Character> = emptyList(),
)

@HiltViewModel
class MainViewModel @Inject constructor(
    val fetchCharactersUseCase: FetchAllCharactersUseCase,
    val getCharacterUseCase: GetCharacterUseCase

) : ViewModel() {

    var uiState = mutableStateOf(CharacterUiState())
        private set

    init {
        fetchCharacters()
    }

    fun deleteItemOnLongPressed(characterId: Int) {
        uiState.value = uiState.value.copy(
            filteredCharacters = uiState.value.filteredCharacters.filterNot { it.id == characterId }
        )
    }

    fun getItemById(characterId: Int) {
        uiState.value.characters.find { it.id == characterId }
    }

    fun onQueryChanged(query: String) {
        uiState.value = uiState.value.copy(
            searchQuery = query,
            filteredCharacters = if (query.isEmpty()) uiState.value.characters else uiState.value.characters.filter {
                it.name?.contains(
                    query,
                    ignoreCase = true
                ) ?: false
            }
        )
    }

    fun fetchCharacters() {
        uiState.value = uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            fetchCharactersUseCase().onSuccess { list ->
                uiState.value = uiState.value.copy(
                    characters = list,
                    filteredCharacters = list,
                    isLoading = false
                )
            }.onFailure { exception ->
                Log.e("MainViewModel", "Error fetching characters: ", exception)
                uiState.value = uiState.value.copy(isLoading = false)
            }
        }
    }
}


