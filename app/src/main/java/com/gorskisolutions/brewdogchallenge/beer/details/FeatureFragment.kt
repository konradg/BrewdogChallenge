package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.domain.Beer
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.toImmutableList
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class FeatureFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecyclerView(inflater.context).apply {
            layoutManager = LinearLayoutManager(
                inflater.context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_TYPE) }?.apply {
            val textView: TextView = view as TextView
            val type = getInt(ARG_TYPE)
            textView.text = type.toString()
            detailsViewModel.beer.observe(viewLifecycleOwner) { beer ->
                if (beer != null) {
                    view.text = provideFeatureDescription(beer, type)
                }
            }
        }
    }

    private fun provideFeatureDescription(beer: Beer, type: Int): String = when (type) {
        TYPE_HOPS -> beer.ingredients.hops.joinToString(separator = "\n")
        TYPE_MALTS -> beer.ingredients.malt.joinToString(separator = "\n")
        TYPE_METHODS -> beer.method.mashingTemp.map { it.toString() }
            .plus(beer.method.fermentation.toString()).run {
                beer.method.twist?.let { this.plus(it) } ?: this
            }.toImmutableList().joinToString(separator = "\n")
        else -> throw IllegalArgumentException("Beer feature type must be one of TYPE_HOPS, TYPE_MALTS, TYPE_METHODS")
    }

    companion object {
        const val ARG_TYPE = "ARG_TYPE"
        const val TYPE_MALTS = 0
        const val TYPE_HOPS = 1
        const val TYPE_METHODS = 2
    }
}
