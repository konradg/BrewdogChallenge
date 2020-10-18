package com.gorskisolutions.brewdogchallenge.beer.details

import android.content.Context
import android.content.Intent

class BeerDetailsIntent : Intent {
    var beerId: String?
        set(value) {
            putExtra(KEY_BEER_ID, value)
        }
        get() {
            return getStringExtra(KEY_BEER_ID)
        }

    constructor(context: Context, beerId: String) : super(
        context,
        BeerDetailsActivity::class.java
    ) {
        this.beerId = beerId
    }

    constructor(intent: Intent) : super(intent)

    companion object {
        const val KEY_BEER_ID = "BeerDetailsIntent.KEY_BEER_ID"
    }
}
