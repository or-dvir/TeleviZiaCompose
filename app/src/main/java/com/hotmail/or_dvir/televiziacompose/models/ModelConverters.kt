package com.hotmail.or_dvir.televiziacompose.models

import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity

fun List<MovieModel>.toMovieEntities(): List<MovieEntity> = map { it.toMovieEntity() }
fun MovieModel.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    plotSummary = plotSummary,
)