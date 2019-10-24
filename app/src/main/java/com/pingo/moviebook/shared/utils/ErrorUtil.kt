package com.pingo.moviebook.shared.utils

import android.text.TextUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Error response parser
 */
class ErrorUtil<T>(ref: Class<T>) {

    private val adapter: JsonAdapter<T>

    init {
        val moshi = Moshi.Builder().build()
        adapter = moshi.adapter(ref)
    }

    fun getError(error: String?): T? {
        return try {
            when {
                error != null && !TextUtils.isEmpty(error) -> adapter.fromJson(error)
                else -> null
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
            null
        }
    }
}
