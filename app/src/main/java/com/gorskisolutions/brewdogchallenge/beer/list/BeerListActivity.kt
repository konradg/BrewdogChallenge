package com.gorskisolutions.brewdogchallenge.beer.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gorskisolutions.brewdogchallenge.beer.details.BeerDetailsIntent
import com.gorskisolutions.brewdogchallenge.databinding.ActivityBeerListBinding
import com.gorskisolutions.brewdogchallenge.ui.SpacesItemDecoration
import com.gorskisolutions.brewdogchallenge.ui.dp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BeerListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var binding: ActivityBeerListBinding
    private lateinit var list: RecyclerView
    private lateinit var adapter: BeerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()

        viewModel.screenState.observe(this) { list -> adapter.list = list }
        adapter.positionClicks.subscribe {
            startActivity(BeerDetailsIntent(this, it))
        }
    }

    private fun setUpList() {
        adapter = BeerListAdapter()
        list = binding.list
        list.setHasFixedSize(true)
        list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        list.adapter = adapter
        list.addItemDecoration(SpacesItemDecoration(8.dp))
    }
}
