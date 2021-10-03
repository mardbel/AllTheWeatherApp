package com.example.allweatherandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allweatherandroid.R
import com.example.allweatherandroid.data.HourlyWeather
import com.example.allweatherandroid.databinding.HourlyForecastListBinding
import com.example.allweatherandroid.databinding.HourlyHeaderBinding

private const val POS_HEADER: Int = 0
private const val POS_ROW: Int = 1


class HourlyForecastAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var hourlyInfo: List<HourlyWeather> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == POS_HEADER) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.hourly_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_list, parent, false)
            RowViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POS_HEADER) {
            (holder as HeaderViewHolder).bind()
        } else {
            (holder as RowViewHolder).bind(hourlyInfo[position-1])
        }
    }

    override fun getItemCount(): Int {
        return hourlyInfo.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            POS_HEADER
        } else POS_ROW
    }

    class HeaderViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var binding = HourlyHeaderBinding.bind(itemView)
        fun bind() {
            binding.timeTv.text = "Time"
            binding.tempTv.text = "Temp"
            binding.rainTv.text = "Chance of Rain"
            binding.windTv.text = "Wind (MPH)"
            binding.humidityTv.text = "Humidity"
        }
    }

    class RowViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var binding = HourlyForecastListBinding.bind(itemView)
        private val time = binding.hourTv
        private val temperature = binding.temperatureTv
        private val chanceOfRain = binding.chanceOfRainTv
        private val wind = binding.windTv
        private val humidity = binding.humidityTv
        fun bind(hourlyInfo: HourlyWeather) {

            time.text = hourlyInfo.hour.toString()
            val tempForm = hourlyInfo.temperature.toString()
            temperature.text = ("$tempForm°").toString()
            val chancePor = (hourlyInfo.rainChance * 100).toInt()
            chanceOfRain.text = ("$chancePor %").toString()
            wind.text = hourlyInfo.windSpeed.toString()
            val humPor = (hourlyInfo.humidity * 100).toInt()
            humidity.text = ("$humPor %").toString()

        }
    }
}

