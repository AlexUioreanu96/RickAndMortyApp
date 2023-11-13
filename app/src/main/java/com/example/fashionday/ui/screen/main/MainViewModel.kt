package com.example.fashionday.ui.screen.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashionday.domain.usecase.Character
import com.example.fashionday.domain.usecase.FetchAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    val fetchCharactersUseCase: FetchAllProductsUseCase
) : ViewModel() {

    var uiState = mutableStateOf(CharacterUiState())
        private set

    init {
        fetchCharacters()
    }

    fun deleteItemOnLongPressed(characterId: Int) {
        uiState = uiState.value.copy(
            products = LinkedHashMap(uiState.products.filterKeys { it != productId })
        )
    }

    fun fetchCharacters() {
        uiState.value = uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            fetchCharactersUseCase().onSuccess { list ->
                uiState.value = uiState.value.copy(characters = list)
            }.onFailure { exception ->
                Log.e("MainViewModel", "Error fetching characters: ", exception)
                uiState.value = uiState.value.copy(isLoading = false)
            }
        }
    }
}


