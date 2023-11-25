package com.plcoding.jetpackcomposepokedex.data.datasource.remote

import com.plcoding.jetpackcomposepokedex.domain.datasource.exception.createExceptionByErrorCode
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemoteDataSource
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import retrofit2.HttpException
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(

) : RemoteDataSource {
    override suspend fun <T> call(request: suspend () -> T): ResultValue<T> =
        checkError(safeApiCall(request = request))

    private suspend fun <T> safeApiCall(request: suspend () -> T): ResultValue<T> =
        try {
            val res = request.invoke()
            ResultValue.Success(res)
        } catch (e: Exception) {
            ResultValue.Error(e)
        }

    private fun <T> checkError(resultValue: ResultValue<T>): ResultValue<T> =
        if (resultValue is ResultValue.Error) {
            val ex = createExceptionByErrorCode(
                errorCode = (resultValue.exception as? HttpException)?.code()
            )
            resultValue.copy(exception = ex)
        } else {
            resultValue
        }

}