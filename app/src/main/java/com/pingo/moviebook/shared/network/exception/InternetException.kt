package com.pingo.moviebook.shared.network.exception

import java.io.IOException

/**
 * Custom exception for Internet connections
 */

class InternetException : IOException(){
    override val message: String?
        get() = "Please check your internet connection"
}