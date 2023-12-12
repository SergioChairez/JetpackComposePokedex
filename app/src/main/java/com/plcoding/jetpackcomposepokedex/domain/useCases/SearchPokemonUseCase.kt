package com.plcoding.jetpackcomposepokedex.domain.useCases

import android.util.Log
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(name: String): ResultValue<PokemonModel> {
        val result = repository.searchPokemon(name)
        Log.d("Result", "Usecase: ${result}")
        return result
    }

}