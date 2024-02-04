package com.example.movie.ui.movielist

import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.domain.model.DiscoverMovie
import com.example.domain.usecase.GetNowPlayingMovieUseCase
import com.example.domain.usecase.GetPopularMovieUseCase
import com.example.domain.usecase.GetTopRatedMovieUseCase
import com.example.domain.usecase.GetUpcomingMovieUseCase
import com.example.domain.utils.Resource
import com.example.movie.BaseViewModelTest
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
class MovieViewModelTest : BaseViewModelTest() {
    private val connectivityObserver: ConnectivityObserver = mockk()
    private val popularMovieUseCase: GetPopularMovieUseCase = mockk()
    private val nowPlayingMovieUseCase: GetNowPlayingMovieUseCase = mockk()
    private val topRatedMovieUseCase: GetTopRatedMovieUseCase = mockk()
    private val upcomingMovieUseCase: GetUpcomingMovieUseCase = mockk()

    private val vm by lazy {
        MovieViewModel(
            connectivityObserver,
            popularMovieUseCase,
            nowPlayingMovieUseCase,
            topRatedMovieUseCase,
            upcomingMovieUseCase
        )
    }

    @Test
    fun `success get popular movies`() = runTest {
        val dummyDiscoverMovie = DiscoverMovie(
            id = 0,
            popularity = 100.0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            title = "",
            posterPath = "",
            overview = "",
            releaseDate = "",
            video = false,
            voteAverage = 2.4,
            voteCount = 0
        )
        val dataDummies = Resource.Success(listOf(dummyDiscoverMovie))
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { popularMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getPopularMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.popularMovie.value
        assertNotNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data?.size, result?.size)
    }

    @Test
    fun `error get popular movies`() = runTest {
        val dataDummies = Resource.Error<List<DiscoverMovie>>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { popularMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getPopularMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.popularMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `success get now playing movies`() = runTest {
        val dummyDiscoverMovie = DiscoverMovie(
            id = 0,
            popularity = 100.0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            title = "",
            posterPath = "",
            overview = "",
            releaseDate = "",
            video = false,
            voteAverage = 2.4,
            voteCount = 0
        )
        val dataDummies = Resource.Success(listOf(dummyDiscoverMovie))
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { nowPlayingMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getNowPlayingMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.nowPlayingMovie.value
        assertNotNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data?.size, result?.size)
    }

    @Test
    fun `error get now playing movies`() = runTest {
        val dataDummies = Resource.Error<List<DiscoverMovie>>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { nowPlayingMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getNowPlayingMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.nowPlayingMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `success get top rated movies`() = runTest {
        val dummyDiscoverMovie = DiscoverMovie(
            id = 0,
            popularity = 100.0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            title = "",
            posterPath = "",
            overview = "",
            releaseDate = "",
            video = false,
            voteAverage = 2.4,
            voteCount = 0
        )
        val dataDummies = Resource.Success(listOf(dummyDiscoverMovie))
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { topRatedMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getTopRatedMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.topRatedMovie.value
        assertNotNull(result)
        assertNotNull(networkConnectivity)
        assertEquals(false, isLoading)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data?.size, result?.size)
    }

    @Test
    fun `error get top rated movies`() = runTest {
        val dataDummies = Resource.Error<List<DiscoverMovie>>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { topRatedMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getTopRatedMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.nowPlayingMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }

    @Test
    fun `success get upcoming movies`() = runTest {
        val dummyDiscoverMovie = DiscoverMovie(
            id = 0,
            popularity = 100.0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            title = "",
            posterPath = "",
            overview = "",
            releaseDate = "",
            video = false,
            voteAverage = 2.4,
            voteCount = 0
        )
        val dataDummies = Resource.Success(listOf(dummyDiscoverMovie))
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { upcomingMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getUpcomingMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.upcomingMovie.value
        assertNotNull(result)
        assertNotNull(networkConnectivity)
        assertEquals(false, isLoading)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
        assertEquals(dataDummies.data?.size, result?.size)
    }

    @Test
    fun `error get upcoming movies`() = runTest {
        val dataDummies = Resource.Error<List<DiscoverMovie>>(message = "error")
        coEvery { connectivityObserver.observe() } returns (flowOf(ConnectivityObserver.Status.Available))
        coEvery { upcomingMovieUseCase.invoke() } returns (flowOf(dataDummies))
        vm.getUpcomingMovie()
        advanceUntilIdle()
        val isLoading = vm.isLoading.value
        val networkConnectivity = vm.networkStatus.value
        val result = vm.upcomingMovie.value
        assertNull(result)
        assertEquals(false, isLoading)
        assertNotNull(networkConnectivity)
        assertEquals(ConnectivityObserver.Status.Available.name, networkConnectivity)
    }
}