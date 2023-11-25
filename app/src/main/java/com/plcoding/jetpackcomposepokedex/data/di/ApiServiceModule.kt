package com.plcoding.jetpackcomposepokedex.data.di

import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiServiceModule {

    @Singleton
    @Provides
    fun providePokeService(@DefaultRetrofitApi retrofit: Retrofit): PokemonApiService =
        retrofit.create(PokemonApiService::class.java)
}