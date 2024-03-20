package com.plcoding.jetpackcomposepokedex.domain.useCases

import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class GetPokemonImageUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(name: String, type: String, description: String):
            ResultValue<Pair<String, String>> {
        return repository.getPokemon(name, type, description)
    }

}