package com.hotmail.or_dvir.database

import java.util.UUID

data class MovieEntity(
    val id: UUID,
    val title: String,
    val plotSummary: String
)
