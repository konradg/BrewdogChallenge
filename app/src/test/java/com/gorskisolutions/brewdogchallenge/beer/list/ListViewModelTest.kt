package com.gorskisolutions.brewdogchallenge.beer.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorskisolutions.brewdogchallenge.TestAppSchedulers
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.ui.ScreenState
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockBeers: List<Beer> = listOf(mockk())
    private val getBeersInteractor: GetBeersInteractor = mockk()
    private val viewModel by lazy { ListViewModel(getBeersInteractor, TestAppSchedulers) }

    @Test
    fun `should fetch beers details on start`() {
        // given
        every { getBeersInteractor.getBeers() } returns Flowable.just(mockBeers)

        // when
        val testObserver = viewModel.screenState.test()

        // then
        testObserver.assertValueHistory(ScreenState.Loading, ScreenState.Success(mockBeers))
        verify { getBeersInteractor.getBeers() }
    }

    @Test
    fun `should show error when loading fails`() {
        // given
        every { getBeersInteractor.getBeers() } returns Flowable.error(
            RuntimeException()
        )

        // when
        val testObserver = viewModel.screenState.test()

        // then
        testObserver.assertValueHistory(ScreenState.Loading, ScreenState.Error)
        verify { getBeersInteractor.getBeers() }
    }
}
