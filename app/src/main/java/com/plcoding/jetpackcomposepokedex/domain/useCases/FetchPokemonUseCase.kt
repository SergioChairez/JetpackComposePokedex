package com.plcoding.jetpackcomposepokedex.domain.useCases

import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class FetchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): ResultValue<PokemonModel> {
        return repository.fetchPokemonInfo(pokemonName = name)
    }
}