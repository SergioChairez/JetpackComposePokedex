package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.domain.useCases.GetPokemonImageUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonImageUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        var showBottomSheet: Boolean = false,
        var name: String = "",
        var type: String = "",
        val isLoaded: Boolean = false,
        val isLoading: Boolean = false,
        val onStart: Boolean = true,
        val onError: Boolean = false,
        val pokemonData: Pair<String, String> = Pair("", ""),
        val errorMessage: String = ""
    )

    fun createPokemon() {
        viewModelScope.launch {
            val currentState = _uiState.value

            when (val result = getPokemonUseCase.invoke(
                currentState.name,
                currentState.type,
                currentState.pokemonData.second
            )) {
                is ResultValue.Success -> {
                    _uiState.value = currentState.copy(
                        isLoaded = true,
                        onStart = false,
                        pokemonData = result.data,
                    )
                }
                is ResultValue.Loading -> {
                    _uiState.value = currentState.copy(
                        isLoading = true,
                        onStart = false
                    )
                }
                is ResultValue.Error -> {
                    _uiState.value = currentState.copy(
                        isLoaded = false,
                        onStart = false,
                        onError = true,
                        errorMessage = "Error: ${result.exception.message ?: ""}"
                    )
                }
            }

        }

    }

    fun showBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = true)
    }

    fun hideBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = false)
    }

}