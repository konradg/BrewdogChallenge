package com.gorskisolutions.brewdogchallenge.beer.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FeaturesAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        FeatureFragment().also {
            it.arguments = Bundle().apply { putInt(FeatureFragment.ARG_TYPE, position) }
        }

}
