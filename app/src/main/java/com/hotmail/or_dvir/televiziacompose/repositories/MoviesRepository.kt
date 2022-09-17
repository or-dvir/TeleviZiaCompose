package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.televiziacompose.models.Movie

interface MoviesRepository {
    suspend fun getAllMovies(): List<Movie>
}