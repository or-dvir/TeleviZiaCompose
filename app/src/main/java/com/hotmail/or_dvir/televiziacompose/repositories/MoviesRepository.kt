package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.televiziacompose.models.MovieModel

interface MoviesRepository {
    suspend fun getAllMovies(): List<MovieModel>
    suspend fun getMoviesForPage(page: Int): List<MovieModel>
    suspend fun getNumPages(): Int
}