package com.hotmail.or_dvir.database.movies

import java.util.UUID

data class MovieEntity(
    val id: UUID,
    val title: String,
    val plotSummary: String
)
