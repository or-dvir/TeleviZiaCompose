package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import androidx.lifecycle.ViewModel
import com.hotmail.or_dvir.televiziacompose.models.Movie
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepository
import com.hotmail.or_dvir.televiziacompose.ui.doInScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class AllMoviesViewModel(
    private val moviesRepo: MoviesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(
        UiState(
            screenState = ScreenState.Loading,
            allMovies = emptyList()
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        doInScope {
            updateUiState(
                _uiState.value.copy(
                    screenState = ScreenState.MovieList,
                    allMovies = moviesRepo.getAllMovies()
                )
            )
        }
    }

    fun handleEvent(event: UiEvent) {
        when(event) {
            is UiEvent.OnMovieClicked -> { /*todo*/ }
        }
    }

    private suspend fun updateUiState(newState: UiState) = _uiState.emit(newState)
}

data class UiState(
    val screenState: ScreenState,
    val allMovies: List<Movie>
)

sealed class UiEvent {
    data class OnMovieClicked(val id: UUID): UiEvent()
}

sealed class ScreenState {
    object Loading: ScreenState()
    object MovieList: ScreenState()
}
