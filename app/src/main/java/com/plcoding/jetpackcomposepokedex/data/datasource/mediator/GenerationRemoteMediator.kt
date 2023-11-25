package com.plcoding.jetpackcomposepokedex.data.datasource.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokedexDataBase
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.toGenerationEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class GenerationRemoteMediator (
    private val pokedexDb: PokedexDataBase,
    private val pokedexApi: PokemonApiService
): RemoteMediator<Int, GenerationEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GenerationEntity>
    ): MediatorResult {

        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        (listOf(lastItem).lastIndex % state.config.pageSize)
                    }
                }
            }
            val generations = pokedexApi.fetchGenerationList(
                limit = state.config.pageSize,
                offset = loadKey
            )

            pokedexDb.withTransaction {
                if ( loadType == LoadType.REFRESH ) {
                    pokedexDb.dao.clearAllGeneration()
                }
                val generationEntities = generations.toGenerationEntity()
                pokedexDb.dao.upsertAllGenerationList(generationEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = generations.next.isNullOrEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}