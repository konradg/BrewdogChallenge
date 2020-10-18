package com.gorskisolutions.brewdogchallenge.beer

import com.gorskisolutions.brewdogchallenge.domain.Beer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class BeerRepositoryTest {
    private val beerService: BeerService = mockk()
    private val beerRepository = BeerRepositoryImpl(beerService)

    private val mockBeer: Beer = mockk {
        every { id } returns "id"
    }

    @Test
    fun `retrieves beers`() {
        // given
        every { beerService.getBeers() } returns Single.just(listOf(mockBeer))

        // when
        val testObserver = beerRepository.getBeers().test()

        // then
        testObserver.assertValue(listOf(mockBeer))
        verify { beerService.getBeers() }
    }

    @Test
    fun `retrieves single beer`() {
        // given
        every { beerService.getBeer("id") } returns Single.just(mockBeer)

        // when
        val testObserver = beerRepository.getBeer("id").test()

        // then
        testObserver.assertValue(mockBeer)
        verify { beerService.getBeer("id") }
    }

    @Test
    fun `gets beer from cache if available`() {
        // given
        every { beerService.getBeers() } returns Single.just(listOf(mockBeer))

        // when
        beerRepository.getBeers().test()
        val testObserver = beerRepository.getBeer("id").test()

        // then
        testObserver.assertValue(mockBeer)
        verify { beerService.getBeers() }
        verify(exactly = 0) { beerService.getBeer(any()) }
    }
}
