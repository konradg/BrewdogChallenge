package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        binding = ActivityBeerDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.viewModel = viewModel

        // TODO replace with proper MaterialToolbar
        supportActionBar?.setIcon(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        setUpViewPager()
        viewModel.beer.observe(this) { beer ->
            binding.beerDetailsContent.show()
            title = beer.name
            GlideApp.with(this)
                .load(beer.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.beerDetailsImage)

            binding.beerDetailsAbv.text = resources.getString(R.string.abv_format, beer.abv)
            binding.beerDetailsDescription.text = beer.description
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
        viewModel.getBeerDetails(intent.beerId)
    }

    private fun setUpViewPager() {
        binding.beerDetalsViewPager.adapter = FeaturesAdapter(supportFragmentManager, lifecycle)
        binding.beerDetalsViewPager.offscreenPageLimit = 2
        TabLayoutMediator(binding.beerDetailsTabs, binding.beerDetalsViewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.beer_features)[position]
        }.attach()
    }
}
