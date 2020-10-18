package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.databinding.ActivityBeerDetailsBinding
import com.gorskisolutions.brewdogchallenge.ui.hide
import com.gorskisolutions.brewdogchallenge.ui.show
import com.gorskisolutions.brewdogchallenge.util.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: ActivityBeerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = BeerDetailsIntent(intent)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_details)
        binding.viewModel = viewModel

        // TODO replace with proper MaterialToolbar
        supportActionBar?.setIcon(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        viewModel.getBeerDetails(intent.beerId)
        viewModel.beer.observe(this) { beer ->
            binding.beerDetailsContent.show()
            title = beer.name
            GlideApp.with(this)
                .load(beer.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_beer)
                .into(binding.imageView)

            binding.loading.hide()
            binding.error.hide()
        }
        viewModel.loading.observe(this) {
            binding.run {
                beerDetailsContent.hide()
                loading.show()
                error.hide()
            }
        }
        viewModel.error.observe(this) {
            binding.run {
                beerDetailsContent.hide()
                loading.hide()
                error.show()
            }
        }
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.beerDetalsViewPager.adapter = FeaturesAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.beerDetailsTabs, binding.beerDetalsViewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.beer_features)[position]
        }.attach()
    }
}
