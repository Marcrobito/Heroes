package com.fintonic.heroes.network

import com.fintonic.heroes.App
import com.fintonic.heroes.common.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import okhttp3.CacheControl


var cacheSize:Long = 10 * 1024 * 1024 // 10 MB
var cache = Cache(App.appInstance.applicationContext.cacheDir, cacheSize)




fun provideClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor)
        .cache(cache)
        .addInterceptor {
            try{

                it.proceed(it.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build())
            }catch (e:Exception){
                val offlineRequest = it.request().newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)

                    .build()
                it.proceed(offlineRequest)
            }
        }.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {


    return  Retrofit.Builder()

        .baseUrl(Constants().BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}


fun provideApiWebService(retrofit: Retrofit):HeroesApi = retrofit.create(HeroesApi::class.java)
