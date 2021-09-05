package com.hotmail.or_dvir.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.UUID
import kotlin.random.Random

object MoviesDatabase
{
    val allMovies = List(
        size = 100,
        init = { index ->
            MovieEntity(UUID.randomUUID(), "movie #$index", "very interesting plot #$index")
        }
    )

    private val mutableFavoriteMovies = MutableLiveData<List<MovieEntity>>()
    val favoriteMovies: LiveData<List<MovieEntity>> = mutableFavoriteMovies

    fun addFavorite(movie: MovieEntity): Boolean
    {
        return mutableFavoriteMovies.let {
            //imitate chance of failure
            if (Random.nextInt(100) >= 80)
            {
                it.value = it.value?.plus(movie) ?: listOf(movie)
                true
            } else
            {
                false
            }
        }
    }

    fun removeFavorite(movie: MovieEntity): Boolean
    {
        return mutableFavoriteMovies.let {
            //imitate chance of failure
            if (Random.nextInt(100) >= 80)
            {
                it.value = it.value?.minus(movie)
                true
            } else
            {
                false
            }
        }
    }
}