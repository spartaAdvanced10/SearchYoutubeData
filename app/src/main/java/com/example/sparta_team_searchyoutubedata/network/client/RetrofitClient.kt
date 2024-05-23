package com.example.sparta_team_searchyoutubedata.network.client

import com.example.sparta_team_searchyoutubedata.network.data.remote.YoutubeDataRemoteResource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val baseUrl = "https://www.googleapis.com/youtube/v3/"

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val youtubeDataRemote: YoutubeDataRemoteResource by lazy {
        retrofit.create(YoutubeDataRemoteResource::class.java)
    }
}
