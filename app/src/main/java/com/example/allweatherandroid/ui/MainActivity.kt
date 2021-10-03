package com.example.allweatherandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.allweatherandroid.data.AllWeatherApp
import com.example.allweatherandroid.databinding.ActivityMainBinding
import com.example.allweatherandroid.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val springDotsIndicator = binding.springDotsIndicator
        val viewPager: ViewPager2 = binding.pager

        binding.ivSearchIcon.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        viewModel.cities.observe(this, Observer { cities ->
            val fragments: List<Fragment> =
                cities.map {
                    CityFragment.create(it.id)
                }
            val arrayListFragments = ArrayList<Fragment>()

            arrayListFragments.addAll(fragments)
            val adapter = ViewPagerAdapter(arrayListFragments, this)
            viewPager.adapter = adapter
            springDotsIndicator.setViewPager2(viewPager)
        })

    }
}