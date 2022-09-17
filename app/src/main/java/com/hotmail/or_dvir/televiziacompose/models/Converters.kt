package com.hotmail.or_dvir.televiziacompose.models

import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity

fun List<Movie>.toMovieEntities(): List<MovieEntity> = map { it.toMovieEntity() }
fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    plotSummary = plotSummary,
)