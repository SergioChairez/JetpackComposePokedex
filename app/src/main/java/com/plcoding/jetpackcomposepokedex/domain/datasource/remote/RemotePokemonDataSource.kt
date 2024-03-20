package com.plcoding.jetpackcomposepokedex.domain.datasource.remote

import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.util.ResultValue

interface RemotePokemonDataSource {

    suspend fun fetchPokemonList(limit: Int, offset: Int): ResultValue<PokemonListModel>

    suspend fun fetchPokemonInfo(pokemonName: String): ResultValue<PokemonModel>

    suspend fun fetchGenerationList(limit: Int, offset: Int): ResultValue<GenerationList>

    suspend fun fetchGenerationVersion(id: Int): ResultValue<List<VersionGroup>>

    suspend fun searchPokemon(name: String): ResultValue<PokemonModel>

    suspend fun getPokemonDescription(name: String, type: String): ResultValue<String>

    suspend fun getPokemonImage(name: String, type: String, description: String): ResultValue<String>

}