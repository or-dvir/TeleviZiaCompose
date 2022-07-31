package com.hotmail.or_dvir.televiziacompose.models

import com.hotmail.or_dvir.database.movies.MovieEntity

fun List<Movie>.toMovieEntities(): List<MovieEntity> = map { it.toMovieEntity() }
fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    plotSummary = plotSummary,
    posterUrl = posterUrl,
    year = year
)

fun List<MovieEntity>.toMovies(): List<Movie> = map { it.toMovie() }
fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    plotSummary = plotSummary,
    posterUrl = posterUrl,
    year = year
)
