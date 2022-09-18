package com.hotmail.or_dvir.televiziacompose.models

import java.util.UUID

data class MovieModel(
    val id: UUID,
    val title: String,
    val plotSummary: String,
)

//todo
//  runtime
//  rating
//  release year
//  cast
//      crew? (director, camera man etc.)
//  poster
//      portrait
//      landscape