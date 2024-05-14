package com.example.sparta_team_searchyoutubedata.network.client

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "AIzaSyDCkxHJXX6PBVxaHBjrKe3EUuPWK3EP4uY"
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(newRequest)
    }
}

