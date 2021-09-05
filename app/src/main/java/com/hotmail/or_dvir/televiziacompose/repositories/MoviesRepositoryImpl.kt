package com.hotmail.or_dvir.televiziacompose.repositories

import androidx.lifecycle.LiveData
import com.hotmail.or_dvir.database.MovieEntity
import com.hotmail.or_dvir.database.MoviesDatabase
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
            MoviesDatabase.allMovies.toMovies()
        }
    }

    override suspend fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDatabase.favoriteMovies
        }
    }

    override suspend fun search(searchQuery: String): List<Movie>
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDatabase.allMovies.filter {
                it.title.contains(searchQuery, true)
            }.toMovies()
        }
    }

    override suspend fun getMovie(movieId: UUID): Movie?
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDatabase.allMovies.find {
                it.id == movieId
            }?.toMovie()
        }
    }

    override suspend fun addFavorite(movie: Movie): Boolean
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDatabase.addFavorite(movie.toMovieEntity())
        }
    }

    override suspend fun removeFavorite(movie: Movie): Boolean
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            MoviesDatabase.removeFavorite(movie.toMovieEntity())
        }
    }
}