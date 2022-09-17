package com.hotmail.or_dvir.televiziacompose.database

import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity
import com.hotmail.or_dvir.televiziacompose.models.Movie

fun List<MovieEntity>.toMovies(): List<Movie> = map { it.toMovie() }
fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    plotSummary = plotSummary
)
