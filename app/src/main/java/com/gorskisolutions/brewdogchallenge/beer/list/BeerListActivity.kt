package com.gorskisolutions.brewdogchallenge.beer.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gorskisolutions.brewdogchallenge.beer.Beer
import com.gorskisolutions.brewdogchallenge.beer.details.BeerDetailsIntent
import com.gorskisolutions.brewdogchallenge.databinding.ActivityBeerListBinding
import com.gorskisolutions.brewdogchallenge.ui.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BeerListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var binding: ActivityBeerListBinding
    private lateinit var beerListAdapter: BeerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()

        viewModel.screenState.observe(this, ::screenStateObserver)
        beerListAdapter.positionClicks.subscribe {
            startActivity(BeerDetailsIntent(this, it))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun screenStateObserver(screenState: ScreenState) = when (screenState) {
        is ScreenState.Success<*> -> showItems(screenState.content as List<Beer>)
        is ScreenState.Loading -> showLoading()
        else -> showError()
    }

    private fun showError() {
        binding.run {
            list.hide()
            error.show()
            loading.hide()
        }
    }

    private fun showLoading() {
        binding.run {
            list.hide()
            error.hide()
            loading.show()
        }
    }

    private fun showItems(content: List<Beer>) {
        beerListAdapter.list = content
        binding.run {
            list.show()
            error.hide()
            loading.hide()
        }
    }

    private fun setUpList() {
        beerListAdapter = BeerListAdapter()
        binding.list.run {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = beerListAdapter
            addItemDecoration(SpacesItemDecoration(8.dp))
        }
    }
}
