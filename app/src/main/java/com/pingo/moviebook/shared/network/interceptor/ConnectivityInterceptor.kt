package com.pingo.moviebook.shared.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Interceptor to check connectivity
 */
class ConnectivityInterceptor : Interceptor {

	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val builder = chain.request().newBuilder()
		return chain.proceed(builder.build())
	}

}