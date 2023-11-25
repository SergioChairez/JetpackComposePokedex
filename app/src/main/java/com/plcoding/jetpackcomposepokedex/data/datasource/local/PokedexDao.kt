package com.plcoding.jetpackcomposepokedex.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokedexDao {

    @Upsert
    suspend fun upsertAllPokemonList(pokemonList: List<PokemonEntity>)

    @Upsert
    suspend fun upsertAllGenerationList(generationList: List<GenerationEntity>)

    @Query("SELECT * FROM pokemonentity")
    fun pagingSourcePokemonList(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemonentity")
    suspend fun clearAllPokemonList()

    @Query("SELECT * FROM generationentity")
    fun pagingSourceGeneration(): PagingSource<Int, GenerationEntity>

    @Query("DELETE FROM generationentity")
    suspend fun clearAllGeneration()
}