package com.plcoding.jetpackcomposepokedex.presentation.pokemonDetailScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.plcoding.jetpackcomposepokedex.domain.models.Pokemon
import com.plcoding.jetpackcomposepokedex.domain.useCases.FetchPokemonUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val fetchPokemonUseCase: FetchPokemonUseCase
) : ViewModel() {

    suspend fun fetchPokemonInfo(pokemonName: String) : ResultValue<Pokemon> {
        return fetchPokemonUseCase.invoke(pokemonName)
    }
}