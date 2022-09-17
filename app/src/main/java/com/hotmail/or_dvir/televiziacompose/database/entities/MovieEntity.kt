package com.hotmail.or_dvir.televiziacompose.database.entities

import java.util.UUID

data class MovieEntity(
    val id: UUID,
    val title: String,
    val plotSummary: String,
)