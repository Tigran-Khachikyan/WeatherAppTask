package com.example.weatherapptask.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptask.R
import com.example.weatherapptask.databinding.ListItemWeatherInfoBinding
import com.example.weatherapptask.domain.weather.models.WeatherInfo

class WeatherInfoAdapter : RecyclerView.Adapter<WeatherInfoAdapter.Holder>() {

    private var infoFields = mutableListOf<InfoField>()

    fun setWeatherInfo(info: WeatherInfo) {
        Log.d("hhh555","setWeatherInfo info: "+ info?.country)

        getInfoList(info)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListItemWeatherInfoBinding.bind(itemView)
        fun bind(field: InfoField) {
            binding.tvInfoTitle.text = itemView.context.getString(field.paramNameRes)
            binding.tvInfoValue.text = field.paramValue
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather_info, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = this.infoFields.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(infoFields[position])
    }

    private fun getInfoList(info: WeatherInfo) {

        infoFields = mutableListOf<InfoField>().apply {
            add(InfoField(R.string.location, info.location))
            add(InfoField(R.string.temperature, info.temperature.toString()))
            add(InfoField(R.string.wind_speed, info.windSpeed.toString()))
            add(InfoField(R.string.humidity, info.humidity.toString()))
            add(InfoField(R.string.visibility, info.visibility.toString()))
            add(InfoField(R.string.sunrise, info.sunrise.toString()))
            add(InfoField(R.string.sunset, info.sunset.toString()))
        }
    }

    data class InfoField(
            val paramNameRes: Int,
            val paramValue: String
    )

}