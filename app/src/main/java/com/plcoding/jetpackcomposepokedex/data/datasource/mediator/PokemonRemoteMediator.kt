package com.plcoding.jetpackcomposepokedex.data.datasource.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokedexDataBase
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.toPokemonEntity
import com.plcoding.jetpackcomposepokedex.domain.datasource.exception.createExceptionByErrorCode
import com.plcoding.jetpackcomposepokedex.util.Constants.INITIAL_LOAD_KEY
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class PokemonRemoteMediator(
    private val pokedexDb: PokedexDataBase,
    private val pokemonApiService: PokemonApiService
): RemoteMediator<Int, PokemonEntity>() {
    var counter = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> {
                    counter = 0
                    INITIAL_LOAD_KEY
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if ( lastItem != null ) {
                        counter++
                    }
                    state.config.pageSize * counter
                }
            }
            val response = pokemonApiService.fetchPokemonList(
                limit = state.config.pageSize,
                offset = loadKey
            )

            val pokemonList = response.toPokemonEntity()

            pokedexDb.withTransaction {
                if ( loadType == LoadType.REFRESH ) {
                    pokedexDb.dao.clearAllPokemonList()
                }
                pokedexDb.dao.upsertAllPokemonList(pokemonList)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.next.isNullOrEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(IOException("Network error", e))
        } catch (e: HttpException) {
            MediatorResult.Error(createExceptionByErrorCode(e.code()))
        }
    }
}