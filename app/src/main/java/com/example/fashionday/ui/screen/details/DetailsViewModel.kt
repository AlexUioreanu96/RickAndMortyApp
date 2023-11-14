package com.example.fashionday.ui.screen.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashionday.domain.model.Character
import com.example.fashionday.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsUiState(
    val character: Character? = null
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    var uiState = mutableStateOf(DetailsUiState())
        private set

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterUseCase.invoke(id).onSuccess {
                uiState.value = uiState.value.copy(character = it)
            }.onFailure { exception ->
                Log.e("DetailsViewModel", "Error getting character: ", exception)
            }
        }
    }
}