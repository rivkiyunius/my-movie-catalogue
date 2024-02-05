package com.example.movie.ui.detail

import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.usecase.DeleteFavoriteMovieUseCase
import com.example.domain.usecase.GetCreditsMovieUseCase
import com.example.domain.usecase.GetDetailMovieUseCase
import com.example.domain.usecase.InsertFavoriteMovieUseCase
import com.example.domain.utils.Resource
import com.example.movie.BaseViewModelTest
import com.example.movie.dummyCredits
import com.example.movie.dummyDetailMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailMovieViewModelTest : BaseViewModelTest() {
    private val connectivityObserver: ConnectivityObserver = mockk()
    private val detailMovieUseCase: GetDetailMovieUseCase = mockk()
    private val getCreditsMovieUseCase: GetCreditsMovieUseCase = mockk()
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase = mockk()
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase = mockk()

    private val vm by lazy {
        DetailMovieViewModel(
            connectivityObserver,
            detailMovieUseCase,
            getCreditsMovieUseCase,
            insertFavoriteMovieUseCase,
            deleteFavoriteMovieUseCase
        )
    }

    @Test
    fun `Success get detail movie`() = runTest {
        val dataDummies = Resource.Success(dummyDetailMovie)
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { detailMovieUseCase(10) } returns (flowOf(dataDummies))
        vm.getDetailMovie(10)
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.detailMovie.value
        assertNotNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data, result)
    }

    @Test
    fun `Error get detail movie`() = runTest {
        val dataDummies = Resource.Error<DetailMovie>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { detailMovieUseCase(10) } returns (flowOf(dataDummies))
        vm.getDetailMovie(10)
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.detailMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `Success get credit movie`() = runTest {
        val dataDummies = Resource.Success(listOf(dummyCredits))
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { getCreditsMovieUseCase(10) } returns (flowOf(dataDummies))
        vm.getCreditMovie(10)
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.creditMovie.value
        assertNotNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data, result)
    }

    @Test
    fun `Error get credit movie`() = runTest {
        val dataDummies = Resource.Error<List<CreditsMovie>>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { getCreditsMovieUseCase(10) } returns (flowOf(dataDummies))
        vm.getCreditMovie(10)
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.creditMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `Save favorite movie`() = runTest {
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { insertFavoriteMovieUseCase(dummyDetailMovie) }
        vm.saveFavoriteMovie()
        advanceUntilIdle()
        val isFavorite = vm.isFavorite.value
        val networkConnectivity = vm.networkStatus.value
        assertEquals(true, isFavorite)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `Delete favorite movie`() = runTest {
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { deleteFavoriteMovieUseCase(10) }
        vm.deleteFavoriteMovie()
        advanceUntilIdle()
        val isFavorite = vm.isFavorite.value
        val networkConnectivity = vm.networkStatus.value
        assertEquals(false, isFavorite)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }
}