package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotmail.or_dvir.televiziacompose.models.MovieModel
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepository
import com.hotmail.or_dvir.televiziacompose.ui.doInScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class AllMoviesViewModel(private val moviesRepo: MoviesRepository): ViewModel() {
    private val TAG = AllMoviesViewModel::class.java.simpleName

    private val _uiState = MutableStateFlow(
        UiState(
            // only thing that matters here is `screenState`
            screenState = AllMoviesScreenState.Loading,
            moviesToDisplay = emptyList(),
            hasNextPage = false
        )
    )

    private var currentPage = 1
    val uiState = _uiState.asStateFlow()

    init {
        doInScope {
            updateUiState(
                _uiState.value.copy(
                    screenState = AllMoviesScreenState.Normal,
                    moviesToDisplay = moviesRepo.getMoviesForPage(currentPage),
                    hasNextPage = hasMorePages()
                )
            )
        }
    }

    fun onUiEvent(event: AllMoviesUiEvent) {
        when(event) {
            is AllMoviesUiEvent.OnMovieClicked -> { /*todo*/ }
            AllMoviesUiEvent.LoadNextPage -> loadNextPage()
        }
    }

    private fun loadNextPage() {
        // prevent duplicate events
        if(_uiState.value.screenState is AllMoviesScreenState.LoadingNextPage) {
            return
        }

        doInScope {
            try {
                updateUiState(
                    _uiState.value.copy(
                        screenState = AllMoviesScreenState.LoadingNextPage
                    )
                )

                val nextPage = moviesRepo.getMoviesForPage(currentPage + 1)
                //if we are here, there was no exception, meaning there IS a next page.
                //so we increment `currentPage`
                currentPage++
                _uiState.apply {
                    updateUiState(
                        value.copy(
                            screenState = AllMoviesScreenState.Normal,
                            moviesToDisplay = value.moviesToDisplay.plus(nextPage),
                            hasNextPage = hasMorePages()
                        )
                    )
                }
            } catch (e: IndexOutOfBoundsException) {
                Log.d(TAG, "no more pages of movies")
                _uiState.apply {
                    updateUiState(
                        value.copy(
                            screenState = AllMoviesScreenState.Normal,
                            hasNextPage = false
                        )
                    )
                }
            }
        }
    }

    private suspend fun hasMorePages() = currentPage < moviesRepo.getNumPages()
    private suspend fun updateUiState(newState: UiState) = _uiState.emit(newState)
}

data class UiState(
    val screenState: AllMoviesScreenState,
    val moviesToDisplay: List<MovieModel>,
    val hasNextPage: Boolean
)

sealed class AllMoviesUiEvent {
    data class OnMovieClicked(val id: UUID): AllMoviesUiEvent()
    object LoadNextPage: AllMoviesUiEvent()
}

sealed class AllMoviesScreenState {
    object Loading: AllMoviesScreenState()
    object LoadingNextPage: AllMoviesScreenState()
    object Normal: AllMoviesScreenState()
}
