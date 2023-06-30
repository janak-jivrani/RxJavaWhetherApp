package com.example.weatherappassignment.presenter

import com.example.weatherappassignment.model.api.NetworkClient
import com.example.weatherappassignment.model.api.NetworkInterface
import com.example.weatherappassignment.callbacks.MainPresenterInterface
import com.example.weatherappassignment.callbacks.MainViewInterface
import com.example.weatherappassignment.model.ForecastResponse
import com.example.weatherappassignment.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class WeatherPresenter(private val view: MainViewInterface) : MainPresenterInterface {

    override fun getWeatherData(cityName:String?) {
        view.showLoading()
        getObservableForWeather(cityName!!)?.subscribeWith(getObserverForWeather())
        getObservableForForecast(cityName!!)?.subscribeWith(getObserverForForecast())
    }

    private fun getObservableForWeather(cityName:String): Observable<WeatherResponse?>? {
        return NetworkClient.getRetrofit()!!.create(NetworkInterface::class.java)
            .getWeatherData(cityName)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserverForWeather(): DisposableObserver<WeatherResponse> {
        return object : DisposableObserver<WeatherResponse>() {
            override fun onNext(t: WeatherResponse) {
                view.displayWeatherData(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                view.displayError("Error fetching weather data")
            }

            override fun onComplete() {}
        }
    }

    private fun getObservableForForecast(cityName:String): Observable<ForecastResponse?>? {
        return NetworkClient.getRetrofit()!!.create(NetworkInterface::class.java)
            .getForecastData(cityName)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserverForForecast(): DisposableObserver<ForecastResponse> {
        return object : DisposableObserver<ForecastResponse>() {
            override fun onNext(t: ForecastResponse) {
                view.displayForecastData(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                view.displayError("Error fetching forecast data")
            }

            override fun onComplete() {}
        }
    }
}