package com.plcoding.jetpackcomposepokedex.domain.repository

import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.Pokemon
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.util.ResultValue

interface PokemonRepository {

    suspend fun fetchPokemonList(limit: Int, offset: Int): ResultValue<PokemonListModel>

    suspend fun fetchPokemonInfo(pokemonName: String): ResultValue<Pokemon>

    suspend fun fetchGenerationList(limit: Int, offset: Int): ResultValue<GenerationList>

}