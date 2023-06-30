package com.example.weatherappassignment.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("city_name")
	val cityName: String? = null,

	@field:SerializedName("data")
	val data: ArrayList<ForecastDataItem>? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("lon")
	val lon: String? = null,

	@field:SerializedName("state_code")
	val stateCode: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)

data class ForecastDataItem(

	@field:SerializedName("pres")
	val pres: Any? = null,

	@field:SerializedName("moon_phase")
	val moonPhase: Any? = null,

	@field:SerializedName("wind_cdir")
	val windCdir: String? = null,

	@field:SerializedName("moonrise_ts")
	val moonriseTs: Any? = null,

	@field:SerializedName("clouds")
	val clouds: Any? = null,

	@field:SerializedName("low_temp")
	val lowTemp: Any? = null,

	@field:SerializedName("wind_spd")
	val windSpd: Any? = null,

	@field:SerializedName("ozone")
	val ozone: Any? = null,

	@field:SerializedName("pop")
	val pop: Any? = null,

	@field:SerializedName("datetime")
	val datetime: String? = null,

	@field:SerializedName("valid_date")
	val validDate: String? = null,

	@field:SerializedName("precip")
	val precip: Any? = null,

	@field:SerializedName("min_temp")
	val minTemp: Any? = null,

	@field:SerializedName("sunrise_ts")
	val sunriseTs: Any? = null,

	@field:SerializedName("weather")
	val weather: ForecastWeather? = null,

	@field:SerializedName("app_max_temp")
	val appMaxTemp: Any? = null,

	@field:SerializedName("max_temp")
	val maxTemp: Any? = null,

	@field:SerializedName("snow_depth")
	val snowDepth: Any? = null,

	@field:SerializedName("max_dhi")
	val maxDhi: Any? = null,

	@field:SerializedName("sunset_ts")
	val sunsetTs: Any? = null,

	@field:SerializedName("clouds_mid")
	val cloudsMid: Any? = null,

	@field:SerializedName("uv")
	val uv: Any? = null,

	@field:SerializedName("vis")
	val vis: Any? = null,

	@field:SerializedName("high_temp")
	val highTemp: Any? = null,

	@field:SerializedName("temp")
	val temp: Any? = null,

	@field:SerializedName("clouds_hi")
	val cloudsHi: Any? = null,

	@field:SerializedName("app_min_temp")
	val appMinTemp: Any? = null,

	@field:SerializedName("moon_phase_lunation")
	val moonPhaseLunation: Any? = null,

	@field:SerializedName("dewpt")
	val dewpt: Any? = null,

	@field:SerializedName("wind_dir")
	val windDir: Any? = null,

	@field:SerializedName("wind_gust_spd")
	val windGustSpd: Any? = null,

	@field:SerializedName("clouds_low")
	val cloudsLow: Any? = null,

	@field:SerializedName("rh")
	val rh: Int? = null,

	@field:SerializedName("slp")
	val slp: Any? = null,

	@field:SerializedName("snow")
	val snow: Any? = null,

	@field:SerializedName("wind_cdir_full")
	val windCdirFull: String? = null,

	@field:SerializedName("moonset_ts")
	val moonsetTs: Any? = null,

	@field:SerializedName("ts")
	val ts: Any? = null
)

data class ForecastWeather(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)
