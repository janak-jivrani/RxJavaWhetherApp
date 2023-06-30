package com.example.weatherappassignment.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class Weather(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class DataItem(

	@field:SerializedName("sunrise")
	val sunrise: String? = null,

	@field:SerializedName("pod")
	val pod: String? = null,

	@field:SerializedName("pres")
	val pres: Any? = null,

	@field:SerializedName("sources")
	val sources: List<String?>? = null,

	@field:SerializedName("ob_time")
	val obTime: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("wind_cdir")
	val windCdir: String? = null,

	@field:SerializedName("lon")
	val lon: Any? = null,

	@field:SerializedName("clouds")
	val clouds: Any? = null,

	@field:SerializedName("wind_spd")
	val windSpd: Any? = null,

	@field:SerializedName("city_name")
	val cityName: String? = null,

	@field:SerializedName("datetime")
	val datetime: String? = null,

	@field:SerializedName("h_angle")
	val hAngle: Any? = null,

	@field:SerializedName("precip")
	val precip: Any? = null,

	@field:SerializedName("station")
	val station: String? = null,

	@field:SerializedName("weather")
	val weather: Weather? = null,

	@field:SerializedName("elev_angle")
	val elevAngle: Any? = null,

	@field:SerializedName("dni")
	val dni: Any? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("uv")
	val uv: Any? = null,

	@field:SerializedName("vis")
	val vis: Any? = null,

	@field:SerializedName("temp")
	val temp: Int? = null,

	@field:SerializedName("dhi")
	val dhi: Any? = null,

	@field:SerializedName("app_temp")
	val appTemp: Any? = null,

	@field:SerializedName("ghi")
	val ghi: Any? = null,

	@field:SerializedName("dewpt")
	val dewpt: Any? = null,

	@field:SerializedName("wind_dir")
	val windDir: Any? = null,

	@field:SerializedName("solar_rad")
	val solarRad: Any? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("rh")
	val rh: Int? = null,

	@field:SerializedName("slp")
	val slp: Any? = null,

	@field:SerializedName("snow")
	val snow: Any? = null,

	@field:SerializedName("sunset")
	val sunset: String? = null,

	@field:SerializedName("aqi")
	val aqi: Any? = null,

	@field:SerializedName("state_code")
	val stateCode: String? = null,

	@field:SerializedName("wind_cdir_full")
	val windCdirFull: String? = null,

	@field:SerializedName("gust")
	val gust: Any? = null,

	@field:SerializedName("ts")
	val ts: Any? = null
)
