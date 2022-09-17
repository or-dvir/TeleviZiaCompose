package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import androidx.lifecycle.ViewModel
import com.hotmail.or_dvir.televiziacompose.models.MovieModel
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
            screenState = AllMoviesScreenState.Loading,
            allMovies = emptyList()
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        doInScope {
            updateUiState(
                _uiState.value.copy(
                    screenState = AllMoviesScreenState.Normal,
                    allMovies = moviesRepo.getAllMovies()
                )
            )
        }
    }

    fun onUiEvent(event: AllMoviesUiEvent) {
        when(event) {
            is AllMoviesUiEvent.OnMovieClicked -> { /*todo*/ }
        }
    }

    private suspend fun updateUiState(newState: UiState) = _uiState.emit(newState)
}

data class UiState(
    val screenState: AllMoviesScreenState,
    val allMovies: List<MovieModel>
)

sealed class AllMoviesUiEvent {
    data class OnMovieClicked(val id: UUID): AllMoviesUiEvent()
}

sealed class AllMoviesScreenState {
    object Loading: AllMoviesScreenState()
    object Normal: AllMoviesScreenState()
}
