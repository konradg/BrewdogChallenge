package com.gorskisolutions.brewdogchallenge.beer.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorskisolutions.brewdogchallenge.TestAppSchedulers
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

class DetailsViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockBeer: Beer = mockk()
    private val getBeerDetailsInteractor: GetBeerDetailsInteractor = mockk()
    private val detailsViewModel = DetailsViewModel(getBeerDetailsInteractor, TestAppSchedulers)

    @Test
    fun `should fetch beer details on start`() {
        // given
        every { getBeerDetailsInteractor.getBeerDetails("id") } returns Flowable.just(mockBeer)
        val testBeerObserver = detailsViewModel.beer.test()
        val testLoadingObserver = detailsViewModel.loading.test()
        val testErrorObserver = detailsViewModel.error.test()

        // when
        detailsViewModel.getBeerDetails("id")

        // then
        testBeerObserver.assertValue(mockBeer)
        testLoadingObserver.assertHasValue()
        testErrorObserver.assertNoValue()
        verify { getBeerDetailsInteractor.getBeerDetails("id") }
    }

    @Test
    fun `should show error when loading fails`() {
        // given
        every { getBeerDetailsInteractor.getBeerDetails("id") } returns Flowable.error(
            RuntimeException()
        )
        val testBeerObserver = detailsViewModel.beer.test()
        val testLoadingObserver = detailsViewModel.loading.test()
        val testErrorObserver = detailsViewModel.error.test()

        // when
        detailsViewModel.getBeerDetails("id")

        // then
        testBeerObserver.assertNoValue()
        testLoadingObserver.assertHasValue()
        testErrorObserver.assertHasValue()
        verify { getBeerDetailsInteractor.getBeerDetails("id") }
    }
}
