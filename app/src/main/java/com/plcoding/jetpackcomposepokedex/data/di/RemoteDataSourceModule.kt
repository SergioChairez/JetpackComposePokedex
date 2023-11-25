package com.plcoding.jetpackcomposepokedex.data.di

import com.plcoding.jetpackcomposepokedex.data.datasource.remote.RemoteDataSourceImpl
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.RemotePokemonDatasourceImpl
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemoteDataSource
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemotePokemonDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Singleton
    @Binds
    fun bindsRemoteDatasource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    fun bindsPokemonDataSource(impl: RemotePokemonDatasourceImpl): RemotePokemonDataSource

}