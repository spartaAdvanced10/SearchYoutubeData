package com.example.sparta_team_searchyoutubedata.network.client

import android.util.Log
import com.example.sparta_team_searchyoutubedata.network.data.remote.YoutubeDataRemoteResource
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.Hashtable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit


object RetrofitClient {
    // cacheDir 설정
    private var cacheDir: File? = null

    private val eTagInterceptor = ETagInterceptor()

    // cacheSize 30mb 설정
    const val cacheSize = 30L * 1024 * 1024

    private val baseUrl = "https://www.googleapis.com/youtube/v3/"

    private lateinit var cache: Cache

    /*
    서버에서 받아오는 eTag를 url을 키로 삼기 위해 ConcurrentHashMap사용
    자매품으로는 HashTable, HashMap 등이 존재
    HashTable의 경우 멀티 스레드 작업은 가능하나 속도가 느림.(tread-safe)
    HashMap은 HashTable의 속도를 개선하고자 나왔으나 단일스레드에서 사용 가능 (tread-safe하지가 않음)
    ConcurrentHashMap는 멀티 스레드 환경에서 사용이 용이(tread-safe)
    구동 원리를 보면 get()메서드에서 synchronized를 사용하지 않아 동기화가 일어나지 않고,put(), remove() 등의 메서드와 동시에 수행 될 수 있음.
    */
    private val eTagMap = ConcurrentHashMap<HttpUrl, String>()

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }


    private val okHttpClient: OkHttpClient by lazy {

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor { chain ->

                eTagInterceptor.intercept(chain)
//                val request1 = chain.request()
//                val url = request1.url
//                val eTag = eTagMap[url]
//                Log.d("eTags","dTag: $eTag")
//
//                val newRequest = if(eTag != null) {
//                    Log.d("eTags","dTag: $eTag")
//                    request1.newBuilder()
//                        .header("If-None-Match", eTag)
//                        .build()
//                }else {
//                    request1
//                }
//                Log.d("RetrofitClient", "request eTag: ${eTag}, null = $request1")
//
//                val response = chain.proceed(newRequest)
//
//                response.header("etag")?.let{ newETag ->
//                    eTagMap[url] = newETag
//                    Log.d("Updated eTagMap", "Updated eTagMap: ${eTagMap}")
//                }
//
//                if (response.code == 304) {
//                    val cacheResponse = response.cacheResponse
//                    cacheResponse?.let {
//                        Log.d("RetrofitClient", "Returning cached response for URL: $url")
//                        return@addInterceptor cacheResponse.newBuilder()
//                            .build()
//                    }
//                }
//                response
            }
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
    fun initCache(cacheDirectory: File) {
        cacheDir = cacheDirectory
        cache = Cache(cacheDir!!, cacheSize)
        Log.d("RetrofitClient", "Cache directory initialized: ${cacheDirectory.path}")
    }
}

class ETagInterceptor : Interceptor {
    private var eTag: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        // If there's an ETag, add it to the request header
        eTag?.let {
            requestBuilder.addHeader("If-None-Match", it)
        }
        val response = chain.proceed(requestBuilder.build())
        // If the response has a new ETag, store it
        if (response.headers != null) {
            eTag = response.header("ETag")
            Log.d("Updated eTagMap2","$eTag")
        }
        Log.d("Updated eTagMap","${response.headers}")
        return response
    }
}

