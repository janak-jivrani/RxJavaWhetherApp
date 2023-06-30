package com.example.weatherappassignment.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappassignment.R
import com.example.weatherappassignment.databinding.ActivityMainBinding
import com.example.weatherappassignment.callbacks.MainViewInterface
import com.example.weatherappassignment.model.ForecastDataItem
import com.example.weatherappassignment.model.ForecastResponse
import com.example.weatherappassignment.model.WeatherResponse
import com.example.weatherappassignment.presenter.WeatherPresenter
import com.example.weatherappassignment.utils.getWeatherIconUrl
import com.example.weatherappassignment.utils.hideKeyboard
import com.example.weatherappassignment.utils.isInternetAvailable
import com.example.weatherappassignment.utils.loadImage
import com.example.weatherappassignment.utils.onSearch
import com.example.weatherappassignment.utils.showAlert
import com.example.weatherappassignment.utils.showKeyboard
import com.example.weatherappassignment.utils.showToast
import com.example.weatherappassignment.views.adapter.ForecastListAdapter
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainViewInterface {

    private lateinit var binding: ActivityMainBinding

    private var presenter: WeatherPresenter? = null

    private var arrayList: ArrayList<ForecastDataItem> = arrayListOf()
    private var mForecastListAdapter: ForecastListAdapter? = null

    private var mCity = "Ahmedabad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        presenter = WeatherPresenter(this)

        loadWeatherApi()
    }

    private fun initActionBar() {
        binding.txtTitle.text = mCity

        binding.imgSearch.clicks()
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                showHideSearchView()
            }

        setUpSearchListener()
    }

    private fun setUpSearchListener() {
        binding.edtSearch.addTextChangedListener {
            if (binding.edtSearch.text.toString().isEmpty()) {
                clearSearched()
            }
        }

        binding.edtSearch.onSearch {
            val searchedText = binding.edtSearch.text?.trim()
            if (searchedText!!.trim().isNotEmpty()) {
                mCity = searchedText.toString()
                loadWeatherApi()
                showHideSearchView()
            } else {
                showToast(getString(R.string.enter_city_name))
            }
        }
    }

    private fun initForecastList() {
        mForecastListAdapter = ForecastListAdapter(this, arrayList)

        binding.recyclerForecast.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mForecastListAdapter
        }
    }

    private fun loadWeatherApi() {
        if (isInternetAvailable()) {
            presenter?.getWeatherData(mCity)
        } else {
            showProgressView(true)
            showAlert(getString(R.string.no_internet), getString(R.string.internet_not_available))
        }
    }

    override fun showLoading() {
        showProgressView(true)
    }

    private fun showHideSearchView() {
        if (binding.cvSearch.visibility == View.GONE) {
            binding.cvSearch.visibility = View.VISIBLE
            binding.edtSearch.showKeyboard()
            binding.edtSearch.requestFocus()
        } else {
            binding.cvSearch.visibility = View.GONE
            binding.edtSearch.hideKeyboard()
        }
    }

    private fun clearSearched() {
        arrayList.clear()
        binding.recyclerForecast.adapter?.notifyDataSetChanged()
    }

    override fun displayWeatherData(weatherResponse: WeatherResponse?) {
        showProgressView(false)
        weatherResponse?.let {
            mCity = it.data?.first()?.cityName!!
            binding.txtTitle.text = mCity

            binding.txtTemp.text = getString(R.string.temp, it.data.first()?.temp.toString())
            binding.txtWeather.text = it.data.first()?.weather?.description
            binding.txtHumidity.text =
                getString(R.string.humidity, it.data.first()?.rh.toString() + "%")
            binding.txtWind.text = getString(R.string.wind, it.data.first()?.windSpd.toString())

            binding.imgWeather.loadImage(getWeatherIconUrl(it.data.first()?.weather?.icon))
        }
    }

    override fun displayForecastData(forecastResponse: ForecastResponse?) {
        arrayList.clear()
        forecastResponse?.let {
            for (i in 1..5) {
                arrayList.add(it.data!![i])
            }
            initForecastList()
        }
    }

    override fun displayError(s: String?) {
        showProgressView(false)
        showAlert(getString(R.string.fail), getString(R.string.something_wrong))
    }

    private fun showProgressView(showProgress: Boolean) {
        binding.scrollMain.visibility = if (showProgress) View.GONE else View.VISIBLE
        binding.progressLoader.visibility = if (showProgress) View.VISIBLE else View.GONE
    }
}