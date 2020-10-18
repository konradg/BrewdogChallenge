package com.gorskisolutions.brewdogchallenge.beer.details.features

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.domain.Malt

class MaltAdapter : ListAdapter<Malt, MaltViewHolder>(MaltItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MaltViewHolder {
        return MaltViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer_feature, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: MaltViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

class MaltViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {
    fun bindTo(malt: Malt) {
        view.text = view.resources.getString(R.string.malt_format, malt.name, malt.amount)
    }
}

class MaltItemDiffCallback : DiffUtil.ItemCallback<Malt>() {
    override fun areItemsTheSame(oldItem: Malt, newItem: Malt): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Malt, newItem: Malt): Boolean = oldItem == newItem
}
