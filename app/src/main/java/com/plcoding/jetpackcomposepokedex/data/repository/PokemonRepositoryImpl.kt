package com.plcoding.jetpackcomposepokedex.data.repository

import com.plcoding.jetpackcomposepokedex.data.qualifiers.IoDispatcher
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemotePokemonDataSource
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.Pokemon
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import kotlinx.coroutines.CoroutineDispatcher
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

    override suspend fun fetchPokemonInfo(pokemonName: String): ResultValue<Pokemon> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchPokemonInfo(pokemonName)
        }

    override suspend fun fetchGenerationList(limit: Int, offset: Int): ResultValue<GenerationList> =
        withContext(coroutineDispatcher) {
            remotePokemonDatasource.fetchGenerationList(limit, offset)
        }

}