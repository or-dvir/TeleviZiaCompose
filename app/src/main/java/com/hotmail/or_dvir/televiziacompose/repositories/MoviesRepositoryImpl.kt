package com.hotmail.or_dvir.televiziacompose.repositories

import androidx.lifecycle.LiveData
import com.hotmail.or_dvir.database.movies.MovieEntity
import com.hotmail.or_dvir.database.movies.MoviesDataSource
import com.hotmail.or_dvir.televiziacompose.models.Movie
import com.hotmail.or_dvir.televiziacompose.models.toMovie
import com.hotmail.or_dvir.televiziacompose.models.toMovieEntity
import com.hotmail.or_dvir.televiziacompose.models.toMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.UUID

class MoviesRepositoryImpl : MoviesRepository
{
    private val ioDispatcher = Dispatchers.IO

    private suspend fun pretendToLoad()
    {
        delay(3000)
    }

    override suspend fun getAllMovies(): List<Movie>
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.allMovies.toMovies()
        }
    }

    override suspend fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.favoriteMovies
        }
    }

    override suspend fun search(searchQuery: String): List<Movie>
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.allMovies.filter {
                it.title.contains(searchQuery, true)
            }.toMovies()
        }
    }

    override suspend fun getMovie(movieId: UUID): Movie?
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.allMovies.find {
                it.id == movieId
            }?.toMovie()
        }
    }

    override suspend fun addFavorite(movie: Movie): Boolean
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.addFavorite(movie.toMovieEntity())
        }
    }

    override suspend fun removeFavorite(movie: Movie): Boolean
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDataSource.removeFavorite(movie.toMovieEntity())
        }
    }
}