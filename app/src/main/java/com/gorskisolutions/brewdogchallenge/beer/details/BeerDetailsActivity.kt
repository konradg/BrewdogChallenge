package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.databinding.ActivityBeerDetailsBinding
import com.gorskisolutions.brewdogchallenge.util.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = BeerDetailsIntent(intent)
        Log.d("Beer details", "Opening details for ${intent.beerId}")
        val binding: ActivityBeerDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_beer_details)
        binding.viewModel = viewModel

        supportActionBar?.setIcon(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)


        viewModel.getBeerDetails(intent.beerId)
        viewModel.beer.observe(this) { beer ->
            if (beer != null) {
                title = beer.name
                GlideApp.with(this)
                    .load(beer.imageUrl)
                    .placeholder(R.drawable.ic_beer)
                    .into(binding.imageView)
            }
        }
    }
}
