package com.example.weatherappassignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappassignment.R
import com.example.weatherappassignment.databinding.RvForcastItemBinding
import com.example.weatherappassignment.model.ForecastDataItem
import com.example.weatherappassignment.utils.getBgColors
import com.example.weatherappassignment.utils.getNewDateFormat
import com.example.weatherappassignment.utils.getWeatherIconUrl
import com.example.weatherappassignment.utils.loadImage

class ForecastListAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ForecastDataItem>
) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvForcastItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class ViewHolder(private val bindingItem: RvForcastItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(item: ForecastDataItem) {
            bindingItem.mcvMain.setCardBackgroundColor(context.getBgColors(adapterPosition))
            bindingItem.txtDay.text = context.getNewDateFormat(item.datetime.toString(), "yyyy-MM-dd", "EEEE").uppercase()
            bindingItem.txtTemp.text = context.getString(R.string.temp, item.temp.toString())
            bindingItem.txtMinTemp.text = context.getString(R.string.temp, item.minTemp.toString())
            bindingItem.txtMaxTemp.text = context.getString(R.string.temp, item.maxTemp.toString())

            bindingItem.imgWeather.loadImage(context.getWeatherIconUrl(item.weather?.icon))
        }
    }
}