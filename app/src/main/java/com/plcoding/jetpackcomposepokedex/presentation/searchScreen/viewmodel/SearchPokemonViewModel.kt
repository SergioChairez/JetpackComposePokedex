package com.plcoding.jetpackcomposepokedex.presentation.searchScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.useCases.SearchPokemonUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPokemonViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    data class UiState(
        val searchError: Boolean = false,
        val pokemon: PokemonModel? = null,
        val isLoading: Boolean = true,
        val errorMessage: String = "",
        var isSearching: Boolean = false
    )

    fun searchPokemon(name: String) {
        viewModelScope.launch {
            if (name.length >= 3) {
                when(val result = searchPokemonUseCase.invoke(name = name)) {
                    is ResultValue.Success -> {
                        _uiState.update {
                            it.copy(
                                searchError = false,
                                pokemon = result.data
                            )
                        }
                    }
                    is ResultValue.Error -> {
                        _uiState.update {
                            it.copy(
                                searchError = true,
                                errorMessage = result.exception.message.toString(),
                            )
                        }
                        Log.d("Pokemon", "${result.exception.message}")
                    }

                    else -> {}
                }
            }
        }
    }

}