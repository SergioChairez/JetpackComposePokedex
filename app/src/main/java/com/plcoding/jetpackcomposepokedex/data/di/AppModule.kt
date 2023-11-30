package com.plcoding.jetpackcomposepokedex.data.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokedexDataBase
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.mediator.PokemonRemoteMediator
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
    internal fun providePokemonListPager(
        pokedexDb: PokedexDataBase,
        pokedexApi: PokemonApiService
    ): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                pokedexDb = pokedexDb,
                pokemonApiService = pokedexApi
            ),
            pagingSourceFactory = {
                pokedexDb.dao.pagingSourcePokemonList()
            }
        )
    }

}