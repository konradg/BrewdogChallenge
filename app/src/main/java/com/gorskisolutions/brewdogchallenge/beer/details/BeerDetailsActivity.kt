package com.gorskisolutions.brewdogchallenge.beer.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gorskisolutions.brewdogchallenge.R

class BeerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)
        val intent = BeerDetailsIntent(intent)
        Log.d("Beer details", "Opening details for ${intent.beerId}")
    }
}
