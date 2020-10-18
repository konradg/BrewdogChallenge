package com.gorskisolutions.brewdogchallenge.beer.details.features

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.domain.BrewMethod

class BrewMethodAdapter(private val beer: Beer) : RecyclerView.Adapter<BrewMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BrewMethodViewHolder {
        // n * mashingParams
        // fermentation
        // twist?
        return BrewMethodViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer_feature, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: BrewMethodViewHolder, position: Int) {
        holder.bindTo(beer.method, position)
    }

    override fun getItemCount(): Int {
        return beer.method.mashingTemp.size + if (beer.method.twist == null) { 1 } else { 2 }
    }
}

class BrewMethodViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {
    fun bindTo(brewMethod: BrewMethod, position: Int) {
        view.text = when {
            position < brewMethod.mashingTemp.size ->  {
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
