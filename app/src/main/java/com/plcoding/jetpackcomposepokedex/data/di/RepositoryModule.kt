package com.plcoding.jetpackcomposepokedex.data.di

import com.plcoding.jetpackcomposepokedex.data.repository.PokemonRepositoryImpl
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

}