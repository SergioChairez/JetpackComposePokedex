package com.plcoding.jetpackcomposepokedex.data.datasource.remote.api

import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchGenerationResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonListModelResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.SearchPokemonResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): FetchPokemonListModelResponseDTO

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): FetchPokemonResponseDTO

    @GET("generation")
    suspend fun fetchGenerationList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): FetchGenerationResponseDTO

    @GET("pokemon-species/{name}")
    suspend fun searchPokemon(
        @Path("name") name: String
    ): SearchPokemonResponseDTO
}