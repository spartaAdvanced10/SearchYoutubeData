package com.example.sparta_team_searchyoutubedata.network.client

import com.example.sparta_team_searchyoutubedata.network.data.remote.SearchYoutubeDataRemoteResource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private val baseUrl = "https://www.googleapis.com/youtube/v3/"

    private val okHttpClient : OkHttpClient by lazy{
        OkHttpClient.Builder()
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

    val search: SearchYoutubeDataRemoteResource by lazy {
        retrofit.create(SearchYoutubeDataRemoteResource::class.java)
    }
}