package com.example.allweatherandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.allweatherandroid.R
import com.example.allweatherandroid.databinding.FragmentCityBinding
import com.example.allweatherandroid.viewmodel.CityFragmentViewModel
import com.example.allweatherandroid.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment(R.layout.fragment_city) {

    private val viewModel by viewModels<CityFragmentViewModel>()
    private var _viewBinding: FragmentCityBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val adapter = HourlyForecastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentCityBinding.bind(requireView())

        val recycler = viewBinding.recyclerHourly
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this.context)


        val idFromArgs = arguments?.getInt("cityId")
        viewModel.getCityById(idFromArgs!!)
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            if (it.id == idFromArgs) {
                viewBinding.cityNameTv.text = it.name
                Glide.with(this)
                    .load(it.mdpi)
                    .into(viewBinding.cityImage)
            }
        })
        viewModel.weather.observe(viewLifecycleOwner, Observer {

            viewBinding.tempDay0Tv.text = it.weatherSpecification.days[0].high.toString()
            viewBinding.tempDay1Tv.text = it.weatherSpecification.days[1].high.toString()
            viewBinding.tempDay2Tv.text = it.weatherSpecification.days[2].high.toString()
            viewBinding.tempDay3Tv.text = it.weatherSpecification.days[3].high.toString()
            viewBinding.tempDay4Tv.text = it.weatherSpecification.days[4].high.toString()
            viewBinding.tempDay5Tv.text = it.weatherSpecification.days[5].high.toString()
            viewBinding.tempDay6Tv.text = it.weatherSpecification.days[6].high.toString()

            adapter.hourlyInfo = it.weatherSpecification.days.firstOrNull()?.hourlyWeather.orEmpty()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object FragmentFactory {
        fun create(id: Int): CityFragment {
            val fragment = CityFragment()
            val args = Bundle()
            args.putInt("cityId", id)
            fragment.arguments = args
            return fragment
        }
    }
}
