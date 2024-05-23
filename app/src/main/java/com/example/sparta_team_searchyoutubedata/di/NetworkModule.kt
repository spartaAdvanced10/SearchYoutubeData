package com.example.sparta_team_searchyoutubedata.di

import com.example.sparta_team_searchyoutubedata.network.client.AuthorizationInterceptor
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.remote.YoutubeDataRemoteResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideYoutubeRemoteData(
        retrofit: Retrofit
    ): YoutubeDataRemoteResource{
        return retrofit.create(YoutubeDataRemoteResource::class.java)
    }
}