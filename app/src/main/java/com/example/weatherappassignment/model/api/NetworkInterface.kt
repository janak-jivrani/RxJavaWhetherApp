package com.example.weatherappassignment.model.api

import com.example.weatherappassignment.model.ForecastResponse
import com.example.weatherappassignment.model.WeatherResponse
import com.example.weatherappassignment.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {
    @GET("current")
    fun getWeatherData(
        @Query("city") cityName: String,
        @Query("units") temp: String = "M",
        @Query("lang") lang: String = "en",
        @Query("key") appid: String? = Constants.API_KEY): Observable<WeatherResponse?>?

    @GET("forecast/daily")
    fun getForecastData(
        @Query("city") cityName: String,
        @Query("units") temp: String = "M",
        @Query("lang") lang: String = "en",
        @Query("key") appid: String? = Constants.API_KEY): Observable<ForecastResponse?>?
}