package com.example.fashionday.data.network.api

import com.example.fashionday.di.HeaderInterceptorQualifier
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@HeaderInterceptorQualifier
class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-mobile-app-locale", "ro_RO")
            .build()
        return chain.proceed(request)
    }
}