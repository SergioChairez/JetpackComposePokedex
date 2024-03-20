package com.plcoding.jetpackcomposepokedex.data.repository

import com.plcoding.jetpackcomposepokedex.data.qualifiers.IoDispatcher
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemotePokemonDataSource
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val remotePokemonDatasource: RemotePokemonDataSource,
): PokemonRepository {
    override suspend fun fetchPokemonList(limit: Int, offset: Int): ResultValue<PokemonListModel> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchPokemonList(limit, offset)
        }

    override suspend fun fetchPokemonInfo(pokemonName: String): ResultValue<PokemonModel> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchPokemonInfo(pokemonName)
        }

    override suspend fun fetchGenerationList(limit: Int, offset: Int): ResultValue<GenerationList> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchGenerationList(limit, offset)
        }

    override suspend fun fetchGenerationVersion(id: Int): ResultValue<List<VersionGroup>> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchGenerationVersion(id)
        }

    override suspend fun searchPokemon(name: String): ResultValue<PokemonModel> =
        withContext(coroutineDispatcher) {
            val result = remotePokemonDatasource.searchPokemon(name)
            result
    }

    override suspend fun getPokemon(
        name: String,
        type: String,
        description: String
    ): Flow<ResultValue<Pair<String, String>>> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.getPokemon(name, type, description)
        }

}