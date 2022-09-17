package com.hotmail.or_dvir.televiziacompose.database

import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity
import com.hotmail.or_dvir.televiziacompose.models.MovieModel

fun List<MovieEntity>.toMovieModels(): List<MovieModel> = map { it.toMovieModel() }
fun MovieEntity.toMovieModel() = MovieModel(
    id = id,
    title = title,
    plotSummary = plotSummary
)
