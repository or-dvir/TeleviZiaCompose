package com.hotmail.or_dvir.televiziacompose.models

import java.util.UUID

data class Movie(
    val id: UUID,
    val title: String,
    val plotSummary: String
)
