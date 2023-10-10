package com.pahada.foundations.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class ApiRepository {

    suspend inline fun <T> safeApiCall(
        crossinline body: suspend () -> T
    ): DataResult<T> {
        return try {
            val result = withContext(Dispatchers.IO) {
                body()
            }
            DataResult.Success(result)
        } catch (e: Exception) {
            DataResult.Error(message = e.message)
        }
    }

}