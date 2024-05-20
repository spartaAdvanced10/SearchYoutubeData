package com.example.sparta_team_searchyoutubedata.network.client

import android.util.Log
import com.example.sparta_team_searchyoutubedata.network.data.remote.YoutubeDataRemoteResource
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitClient{
    // cacheDir 설정
    private var cacheDir : File? = null
    // cacheSize 30mb 설정
    const val cacheSize = 30L * 1024 * 1024

    private val baseUrl = "https://www.googleapis.com/youtube/v3/"

    private lateinit var cache: Cache

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }


    private val okHttpClient : OkHttpClient by lazy{

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthorizationInterceptor())
            // cache 설정
            .addInterceptor { chain ->
                val response = chain.proceed(chain.request())
                // 캐쉬 사용 시 Response from: CACHE, 네트워크 요청 시 Response from: NETWORK - 로그 확인용
                Log.d("NetworkInterceptor", "Response from: ${if (response.cacheResponse != null) "CACHE" else "NETWORK"}")
                response
            }
            // okhttp 사용
            .cache(cache)
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

    // cache 설정
    fun initCache(cacheDirectory: File){
        cacheDir = cacheDirectory
        cache = Cache(cacheDir!!, cacheSize)
        Log.d("RetrofitClient", "Cache directory initialized: ${cacheDirectory.path}")
    }
}