package com.example.favorite.ui.favoritelist

import com.example.domain.usecase.GetFavoriteMoviesUseCase
import com.example.favorite.BaseViewModelTest
import com.example.favorite.dummyFavoriteMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest: BaseViewModelTest() {
    private val getFavoriteViewModel: GetFavoriteMoviesUseCase = mockk()

    private val vm by lazy {
        FavoriteViewModel(getFavoriteViewModel)
    }

    @Test
    fun `Get favorite movie`() = runTest {
        coEvery { getFavoriteViewModel() } returns (flowOf(listOf(dummyFavoriteMovie)))
        vm.getFavoriteMovie()
        advanceUntilIdle()
        val data = vm.favoriteMovies.value
        assertNotNull(data)
        assertEquals(listOf(dummyFavoriteMovie), data)
        assertEquals(listOf(dummyFavoriteMovie).size, data?.size)
    }
}