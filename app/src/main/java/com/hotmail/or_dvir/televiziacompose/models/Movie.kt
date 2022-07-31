package com.hotmail.or_dvir.televiziacompose.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import java.util.UUID

data class Movie(
    val id: UUID,
    val title: String,
    val plotSummary: String,
    val posterUrl: String,
    val year: Int
) {
    companion object {
        fun dummy(): Movie {
            val titleNumWords = 1..5
            val plotNumWords = 1..65
            val years = 1900..2022

            return Movie(
                id = UUID.randomUUID(),
                title = LoremIpsum(titleNumWords.random()).values.joinToString(),
                plotSummary = LoremIpsum(plotNumWords.random()).values.joinToString(),
                posterUrl = "https://lh3.googleusercontent.com/1x5fLPAQpaacJLrbzhtAI8VK6IkIwLe1hErnsTuhbOu9VW5UNaCF9ecM7HuTtu2ZRYpixIB80ROCw1CSEuzaF0Gm8iED4YZVLg=w843",
                year = years.random()
            )
        }
    }
}
