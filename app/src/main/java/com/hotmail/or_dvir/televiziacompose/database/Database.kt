package com.hotmail.or_dvir.televiziacompose.database

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.hotmail.or_dvir.televiziacompose.database.entities.MovieEntity
import java.util.UUID
import kotlin.random.Random
object Database {
    private const val ITEMS_PER_PAGE = 25
    private val numPages get() = allMovies.size / ITEMS_PER_PAGE

    val allMovies = MutableList(100) {
        MovieEntity(
            id = UUID.randomUUID(),
            title = randomTitle(),
            plotSummary = randomPlot()
        )
    }

    fun getMoviesForPage(page: Int): List<MovieEntity> {
        if(page !in 1..numPages) {
            throw IndexOutOfBoundsException("`page` must be in the range [1, numPages]. was: $page")
        }

        return allMovies.chunked(ITEMS_PER_PAGE)[page - 1]
    }

    private fun randomPlot() = LoremIpsum(Random.nextInt(1, 100)).values.joinToString(" ")
    private fun randomTitle() = LoremIpsum(Random.nextInt(1, 10)).values.joinToString(" ")
}

