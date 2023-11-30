package com.plcoding.jetpackcomposepokedex.data.datasource.remote

import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.asDomain
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemoteDataSource
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemotePokemonDataSource
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

internal class RemotePokemonDatasourceImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService,
    private val remoteDataSource: RemoteDataSource
) : RemotePokemonDataSource {
    override suspend fun fetchPokemonList(
        limit: Int,
        offset: Int
    ): ResultValue<PokemonListModel> {
        return remoteDataSource.call {
            pokemonApiService.fetchPokemonList(
                limit = limit,
                offset = offset
            ).asDomain()
        }
    }

    override suspend fun fetchPokemonInfo(
        pokemonName: String
    ): ResultValue<PokemonModel> {
        return remoteDataSource.call {
            pokemonApiService.fetchPokemonInfo(
                name = pokemonName
            ).asDomain()
        }
    }

    override suspend fun fetchGenerationList(
        limit: Int,
        offset: Int
    ): ResultValue<GenerationList> {
        return remoteDataSource.call {
            pokemonApiService.fetchGenerationList(
                limit = limit,
                offset = offset
            ).asDomain()
        }
    }

}