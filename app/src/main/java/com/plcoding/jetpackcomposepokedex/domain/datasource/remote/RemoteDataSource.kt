package com.plcoding.jetpackcomposepokedex.domain.datasource.remote

import com.plcoding.jetpackcomposepokedex.util.ResultValue

interface RemoteDataSource {

    suspend fun <T> call(request: suspend () -> T): ResultValue<T>
}