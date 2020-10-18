package com.gorskisolutions.brewdogchallenge.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.beer.Beer
import com.gorskisolutions.brewdogchallenge.databinding.ItemBeerBinding

class BeerListAdapter : RecyclerView.Adapter<BeerViewHolder>() {

    var list: List<Beer> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder(
            ItemBeerBinding.inflate(LayoutInflater.from(parent.context))
                .root
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = list[position]
        holder.binding.beerName.text = item.name
        holder.binding.beerAbv.text =
            holder.binding.beerAbv.resources.getString(R.string.abv_format, item.abv)
        Glide.with(holder.binding.beerLabel)
            .load(item.imageUrl)
            .into(holder.binding.beerLabel)
    }

    override fun getItemCount(): Int = list.size
}

class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemBeerBinding.bind(itemView)
}
