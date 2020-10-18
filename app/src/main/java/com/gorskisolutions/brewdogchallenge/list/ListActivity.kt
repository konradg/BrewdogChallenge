package com.gorskisolutions.brewdogchallenge.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gorskisolutions.brewdogchallenge.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        viewModel.beers.observe(this) { list ->
            Log.d("Beer list", "List received with ${list.size} elements")
            list.forEach { Log.d("Beer list", "Beer ${it.id}: ${it.name}") }
        }
    }
}
