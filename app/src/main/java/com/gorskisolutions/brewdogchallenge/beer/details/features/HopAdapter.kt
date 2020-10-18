package com.gorskisolutions.brewdogchallenge.beer.details.features

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.domain.Hop

class HopAdapter(beer: Beer) : ListAdapter<Hop, HopViewHolder>(HopItemDiffCallback()) {

    init {
        submitList(beer.ingredients.hops)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HopViewHolder {
        return HopViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer_feature, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: HopViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

class HopViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {
    fun bindTo(hop: Hop) {
        view.text = view.resources.getString(R.string.hop_format, hop.name, hop.amount, hop.add, hop.attribute)
    }
}

class HopItemDiffCallback : DiffUtil.ItemCallback<Hop>() {
    override fun areItemsTheSame(oldItem: Hop, newItem: Hop): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Hop, newItem: Hop): Boolean = oldItem == newItem
}
