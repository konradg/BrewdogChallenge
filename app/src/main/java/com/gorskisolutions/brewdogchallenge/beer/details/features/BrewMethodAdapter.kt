package com.gorskisolutions.brewdogchallenge.beer.details.features

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.domain.BrewMethod

class BrewMethodAdapter : RecyclerView.Adapter<BrewMethodViewHolder>() {

    private var beer: Beer? = null

    fun acceptBeer(beer: Beer) {
        this.beer = beer
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BrewMethodViewHolder {
        return BrewMethodViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer_feature, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: BrewMethodViewHolder, position: Int) {
        beer?.let { holder.bindTo(it.method, position) }
    }

    override fun getItemCount(): Int {
        return beer?.let {
            it.method.mashingTemp.size + (it.method.twist?.let { 2 } ?: 1)
        } ?: 0
    }
}

class BrewMethodViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {
    fun bindTo(brewMethod: BrewMethod, position: Int) {
        view.text = when {
            position < brewMethod.mashingTemp.size -> {
                val mashing = brewMethod.mashingTemp[position]
                val mashTime = mashing.duration?.let {
                    view.resources.getString(R.string.mashing_time_format, mashing.duration)
                } ?: ""
                val mashTemp = view.resources.getString(R.string.mashing_temp_format, mashing.temp)
                "$mashTemp $mashTime"
            }
            position == brewMethod.mashingTemp.size ->
                view.resources.getString(R.string.fermentation_format, brewMethod.fermentation.temp)
            else -> brewMethod.twist
        }
    }
}
