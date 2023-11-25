package com.plcoding.jetpackcomposepokedex.data.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokedexDataBase
import com.plcoding.jetpackcomposepokedex.data.datasource.mediator.GenerationRemoteMediator
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokedexDatabase(@ApplicationContext context: Context): PokedexDataBase {
        return Room.databaseBuilder(
            context,
            PokedexDataBase::class.java,
            "pokedex.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGenerationListPager(
        pokedexDb: PokedexDataBase,
        pokedexApi: PokemonApiService
    ): Pager<Int, GenerationEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = GenerationRemoteMediator(
                pokedexDb = pokedexDb,
                pokedexApi = pokedexApi
            ),
            pagingSourceFactory = {
                pokedexDb.dao.pagingSourceGeneration()
            }
        )
    }

}