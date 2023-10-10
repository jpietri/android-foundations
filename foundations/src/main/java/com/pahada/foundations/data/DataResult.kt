package com.pahada.foundations.data

import androidx.annotation.StringRes

/**
 * Result of a data operation.
 * Typically an API call.
 * Either success or failure.
 */
sealed class DataResult<out T> {

    /**
     * Successful data operation
     * @param data returned data if any
     */
    data class Success<out T : Any?>(val data: T? = null) : DataResult<T>()

    /**
     * Failed data operation
     * @param messageId error message ID
     */
    data class Error(
        @StringRes val messageId: Int? = null,
        val message: String? = null
    ) : DataResult<Nothing>()

}
