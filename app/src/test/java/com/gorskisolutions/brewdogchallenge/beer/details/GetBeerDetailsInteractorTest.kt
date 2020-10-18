package com.gorskisolutions.brewdogchallenge.beer.details

import com.gorskisolutions.brewdogchallenge.beer.BeerRepository
import com.gorskisolutions.brewdogchallenge.domain.Beer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

class GetBeerDetailsInteractorTest {
    private val beerRepository: BeerRepository = mockk()
    private val getBeerDetailsInteractor = GetBeerDetailsInteractor(beerRepository)

    private val mockBeer: Beer = mockk()

    @Test
    fun `gets beer from repository`() {
        // given
        every { beerRepository.getBeer("id") } returns Flowable.just(mockBeer)

        // when
        val testObserver = getBeerDetailsInteractor.getBeerDetails("id").test()

        // then
        testObserver.assertValue(mockBeer)
        verify { beerRepository.getBeer("id") }
    }
}
