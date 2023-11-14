package com.example.fashionday

import com.example.fashionday.domain.model.Character
import com.example.fashionday.domain.usecase.FetchAllCharactersUseCase
import com.example.fashionday.domain.usecase.GetCharacterUseCase
import com.example.fashionday.ui.screen.main.CharacterUiState
import com.example.fashionday.ui.screen.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ExampleUnitTest {

    @RelaxedMockK
    private val fetchCharactersUseCase = mockk<FetchAllCharactersUseCase>()

    @RelaxedMockK
    private val getCharacterUseCase = mockk<GetCharacterUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = MainViewModel(fetchCharactersUseCase, getCharacterUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cancel()
    }


    //MainViewModelTest's
    @Test
    fun `initial state is correct`() {
        val initialState = CharacterUiState()
        assertEquals(initialState.copy(isLoading = true), viewModel.uiState.value)
    }

    @Test
    fun `fetchCharacters updates state correctly`() {
        coEvery { fetchCharactersUseCase() } returns Result.success(listOf<Character>())
        viewModel.fetchCharacters()
        assertEquals(
            CharacterUiState(
                isLoading = true,
                characters = emptyList(),
                filteredCharacters = emptyList()
            ), viewModel.uiState.value
        )
    }


}