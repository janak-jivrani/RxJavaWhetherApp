package com.example.weatherappassignment.callbacks

import com.example.weatherappassignment.model.ForecastResponse
import com.example.weatherappassignment.model.WeatherResponse

interface MainViewInterface {
    fun showLoading()
    fun displayWeatherData(weatherResponse: WeatherResponse?)
    fun displayForecastData(forecastResponse: ForecastResponse?)
    fun displayError(s: String?)
}