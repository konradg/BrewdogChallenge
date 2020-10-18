package com.gorskisolutions.brewdogchallenge.beer.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gorskisolutions.brewdogchallenge.R
import com.gorskisolutions.brewdogchallenge.beer.Beer
import com.gorskisolutions.brewdogchallenge.databinding.ItemBeerBinding
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.PublishSubject

class BeerListAdapter : RecyclerView.Adapter<BeerViewHolder>() {

    var list: List<Beer> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    private val clickSubject = PublishSubject.create<String>()
    val positionClicks: Flowable<String> = clickSubject.toFlowable(BackpressureStrategy.LATEST)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder(clickSubject,
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
            .placeholder(R.drawable.image_placeholder)
            .into(holder.binding.beerLabel)
        holder.clickListener.itemId = item.id
    }

    override fun getItemCount(): Int = list.size

}

class BeerViewHolder(clickSubject: PublishSubject<String>, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val binding = ItemBeerBinding.bind(itemView)
    val clickListener = IdClickListener(clickSubject)
        .also { binding.root.setOnClickListener(it) }
}

class IdClickListener(
    private val subject: PublishSubject<String>
) : View.OnClickListener {

    var itemId: String? = null

    override fun onClick(v: View?) {
        itemId?.let(subject::onNext)
    }
}
