package com.pingo.moviebook.shared.utils

import com.pingo.moviebook.shared.models.BaseError
import com.pingo.moviebook.shared.network.exception.InternetException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Utility to parse error response to [BaseError]
 */
object ErrorHandler {

    const val INTERNET_ERROR = "net"
    const val UNKNOWN_ERROR = "Something went wrong. Please try later."

    /**
     * Parse error using [ErrorUtil] Moshi converters
     */
    fun getError(error: Throwable?): String {

        try {
            when (error) {
                is InternetException, is UnknownHostException, is SocketTimeoutException -> return INTERNET_ERROR
                is IOException -> error.message?.let { return it }
                is HttpException -> {
                    val errorBody = error.response()?.errorBody()?.string()
                    val errors: BaseError? = ErrorUtil(BaseError::class.java).getError(errorBody)
                    return errors?.statusMessage ?: UNKNOWN_ERROR
                }
            }
        } catch (exp: Exception) {
            error?.printStackTrace()
            return UNKNOWN_ERROR
        }
        return UNKNOWN_ERROR
    }


    /**
     * Parse error using [ErrorUtil] Moshi converters
     */
    fun getError(error: String?): String {
        return try {
            val errors: BaseError? = ErrorUtil(BaseError::class.java).getError(error)
            errors?.statusMessage ?: UNKNOWN_ERROR
        } catch (exp: Exception) {
            UNKNOWN_ERROR
        }
    }

}
