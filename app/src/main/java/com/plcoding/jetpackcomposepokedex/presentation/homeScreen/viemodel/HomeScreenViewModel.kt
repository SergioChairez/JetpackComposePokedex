package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.domain.useCases.GetPokemonDescriptionUseCase
import com.plcoding.jetpackcomposepokedex.domain.useCases.GetPokemonImageUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPokemonDescriptionUseCase: GetPokemonDescriptionUseCase,
    private val getPokemonImageUseCase: GetPokemonImageUseCase
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
        val description: String = "",
        val url: String = ""
    )

    fun createPokemon() {
        viewModelScope.launch {
            val currentState = _uiState.value

            when (val result = getPokemonDescriptionUseCase.invoke(
                currentState.name,
                currentState.type
            )) {
                is ResultValue.Success -> {
                    _uiState.value = currentState.copy(
                        isLoaded = true,
                        onStart = false,
                        description = result.data,
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
                        description = "Error: ${result.exception.message ?: ""}"
                    )
                }
            }

            when (val result = getPokemonImageUseCase.invoke(
                currentState.name,
                currentState.type,
                currentState.description
            )) {
                is ResultValue.Success -> {
                    _uiState.value = currentState.copy(
                        isLoaded = true,
                        onStart = false,
                        url = result.data,
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
                        url = "Error: ${result.exception.message ?: ""}"
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