package com.pingo.moviebook.shared.models

import com.squareup.moshi.Json

/**
 * Base Error to parse errors
 */
class BaseError {

    @field:Json(name = "status_message")
    var statusMessage: String? = null

    @field:Json(name = "success")
    var success: Boolean? = null


    @field:Json(name = "status_code")
    var statusCode: Int? = null

}



