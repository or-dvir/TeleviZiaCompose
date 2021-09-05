package com.hotmail.or_dvir.televiziacompose.models

import com.hotmail.or_dvir.database.MovieEntity

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(id, title, plotSummary)
fun List<Movie>.toMovieEntities(): List<MovieEntity> =
    map { MovieEntity(it.id, it.title, it.plotSummary) }

fun MovieEntity.toMovie(): Movie = Movie(id, title, plotSummary)
fun List<MovieEntity>.toMovies(): List<Movie> = map { Movie(it.id, it.title, it.plotSummary) }