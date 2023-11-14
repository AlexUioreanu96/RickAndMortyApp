package com.example.fashionday.ui.screen.details

import androidx.lifecycle.ViewModel
import com.example.fashionday.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    init {

    }
}