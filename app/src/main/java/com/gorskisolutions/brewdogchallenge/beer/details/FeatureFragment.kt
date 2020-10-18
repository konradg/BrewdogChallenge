package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorskisolutions.brewdogchallenge.beer.details.features.BrewMethodAdapter
import com.gorskisolutions.brewdogchallenge.beer.details.features.HopAdapter
import com.gorskisolutions.brewdogchallenge.beer.details.features.MaltAdapter
import com.gorskisolutions.brewdogchallenge.domain.Beer
import dagger.hilt.android.AndroidEntryPoint

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
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_TYPE) }?.apply {
            val recyclerView: RecyclerView = view as RecyclerView
            val type = getInt(ARG_TYPE)
            recyclerView.adapter = provideAdapter(type)
            detailsViewModel.beer.observe(viewLifecycleOwner) { beer ->
                submitBeer(beer, type, recyclerView)
            }
        }
    }

    private fun submitBeer(beer: Beer, type: Int, recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter
        when (type) {
            TYPE_HOPS -> (adapter as HopAdapter).submitList(beer.ingredients.hops)
            TYPE_MALTS -> (adapter as MaltAdapter).submitList(beer.ingredients.malt)
            TYPE_METHODS -> (adapter as BrewMethodAdapter).acceptBeer(beer)
        }
    }

    private fun provideAdapter(type: Int): RecyclerView.Adapter<out RecyclerView.ViewHolder> =
        when (type) {
            TYPE_HOPS -> HopAdapter()
            TYPE_MALTS -> MaltAdapter()
            TYPE_METHODS -> BrewMethodAdapter()
            else -> throw IllegalArgumentException("Beer feature type must be one of TYPE_HOPS, TYPE_MALTS, TYPE_METHODS")
        }

    companion object {
        const val ARG_TYPE = "ARG_TYPE"
        const val TYPE_MALTS = 0
        const val TYPE_HOPS = 1
        const val TYPE_METHODS = 2
    }
}
