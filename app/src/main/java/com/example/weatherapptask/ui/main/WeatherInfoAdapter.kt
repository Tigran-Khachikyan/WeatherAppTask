package com.example.weatherapptask.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptask.R
import com.example.weatherapptask.data.network.mappers.UNKNOWN
import com.example.weatherapptask.data.network.mappers.UNKNOWN_HUMIDITY
import com.example.weatherapptask.data.network.mappers.UNKNOWN_TEMP_SEED
import com.example.weatherapptask.databinding.ListItemWeatherInfoBinding
import com.example.weatherapptask.domain.weather.models.WeatherInfo

class WeatherInfoAdapter : RecyclerView.Adapter<WeatherInfoAdapter.Holder>() {

    private var infoFields = mutableListOf<InfoField>()

    fun setWeatherInfo(info: WeatherInfo) {
        getInfoList(info)
        notifyDataSetChanged()
    }

    fun clearInfo() {
        infoFields.clear()
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
            add(InfoField(R.string.country, info.country))
            val temp = info.temperature.let { if (it == UNKNOWN_TEMP_SEED) UNKNOWN else "$it ${info.unit.tempUnit}" }
            add(InfoField(R.string.temperature, temp))
            val speed = info.windSpeed.let { if (it == UNKNOWN_TEMP_SEED) UNKNOWN else "$it ${info.unit.speedUnit}" }
            add(InfoField(R.string.wind_speed, speed))
            val hum = info.humidity.let { if (it == UNKNOWN_HUMIDITY) UNKNOWN else "$it %" }
            add(InfoField(R.string.humidity, hum))
            add(InfoField(R.string.visibility, info.visibility))
            add(InfoField(R.string.sunrise, info.sunrise))
            add(InfoField(R.string.sunset, info.sunset))
        }
    }

    data class InfoField(
            val paramNameRes: Int,
            val paramValue: String
    )
}