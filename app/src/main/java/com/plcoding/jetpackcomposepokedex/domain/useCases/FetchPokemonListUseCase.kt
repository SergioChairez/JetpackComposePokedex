package com.plcoding.jetpackcomposepokedex.domain.useCases

import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class FetchPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): ResultValue<PokemonListModel> {
        return repository.fetchPokemonList(limit,offset)
    }
}