package com.example.allweatherandroid.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.allweatherandroid.R
import com.example.allweatherandroid.databinding.ActivitySearchBinding
import com.example.allweatherandroid.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchCityEt.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                val searchedCity: String = binding.searchCityEt.text.toString()
                viewModel.getRemoteCities(searchedCity)
                //hides keyboard
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(binding.searchCityEt.windowToken, 0)
            }
            false
        }

        viewModel.state.observe(this, Observer {
            when (it) {
                is SearchViewModel.State.Failure -> displayError(it.cause)
                is SearchViewModel.State.Loading -> showProgressBar()
                is SearchViewModel.State.Success -> navigateToMainActivity()
            }
        })
    }

    private fun displayError(error: Throwable) {
        binding.progressBar.isVisible = false
        var layout = binding.rootLayout
        val snackbar = Snackbar.make(
            layout, getString(R.string.search_error, error.message ),
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@SearchActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.progressBar.isVisible = true
    }
}