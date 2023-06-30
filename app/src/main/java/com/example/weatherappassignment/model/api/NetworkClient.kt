package com.example.weatherappassignment.model.api

import com.example.weatherappassignment.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    var retrofit1: Retrofit? = null
    fun getRetrofit(): Retrofit? {
        if (retrofit1 == null) {
            val builder = OkHttpClient.Builder()
            val okHttpClient: OkHttpClient = builder.build()
            retrofit1 = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit1
    }
}