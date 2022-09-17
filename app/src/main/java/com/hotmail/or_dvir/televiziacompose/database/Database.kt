package com.hotmail.or_dvir.televiziacompose.database

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity
import java.util.UUID
import kotlin.random.Random

object Database {
    val allMovies = MutableList(100) {
        MovieEntity(
            id = UUID.randomUUID(),
            title = randomTitle(),
            plotSummary = randomPlot()
        )
    }

    private fun randomPlot() = LoremIpsum(Random.nextInt(1, 100)).values.joinToString(" ")
    private fun randomTitle() = LoremIpsum(Random.nextInt(1, 10)).values.joinToString(" ")
}