package com.gorskisolutions.brewdogchallenge.beer.list

import com.gorskisolutions.brewdogchallenge.beer.BeerRepository
import com.gorskisolutions.brewdogchallenge.domain.Beer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

class GetBeersInteractorTest {
    private val beerRepository: BeerRepository = mockk()
    private val getBeersInteractor = GetBeersInteractor(beerRepository)

    private val mockBeers: List<Beer> = listOf(mockk())

    @Test
    fun `gets beers from repository`() {
        // given
        every { beerRepository.getBeers() } returns Flowable.just(mockBeers)

        // when
        val testObserver = getBeersInteractor.getBeers().test()

        // then
        testObserver.assertValue(mockBeers)
        verify { beerRepository.getBeers() }
    }
}
