package com.hotmail.or_dvir.televiziacompose.repositories

import androidx.lifecycle.LiveData
import com.hotmail.or_dvir.database.movies.MovieEntity
import com.hotmail.or_dvir.televiziacompose.models.Movie
import java.util.UUID

interface MoviesRepository
{
    suspend fun getAllMovies(): List<Movie>

    //returning a database entity here because the database is not familiar with app models...
    //so the conversion will have to be made in the view model in this case.
    suspend fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    suspend fun search(searchQuery: String): List<Movie>
    suspend fun getMovie(movieId: UUID): Movie?

    /**
     * @return Boolean `true` if successful, `false` otherwise
     */
    suspend fun addFavorite(movie: Movie): Boolean

    /**
     * @return Boolean `true` if successful, `false` otherwise
     */
    suspend fun removeFavorite(movie: Movie): Boolean
}