package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val contentError: Boolean = false,
        var showBottomSheet: Boolean = false,
        val isLoading: Boolean = false,
        val isStart: Boolean = true,
        val errorMessage: String = ""
    )

    fun showBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = true)
    }

    fun hideBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = false)
    }

    fun sendPokemonInfo() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            isLoading = true,
            isStart = false
        )
    }
}