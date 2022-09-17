package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.televiziacompose.database.Database
import com.hotmail.or_dvir.televiziacompose.database.toMovies
import com.hotmail.or_dvir.televiziacompose.models.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val scopeThatShouldNotBeCancelled: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : MoviesRepository {

    private suspend fun pretendToLoad() = delay(3000)

    override suspend fun getAllMovies(): List<Movie> {
        return withContext(dispatcher) {
            pretendToLoad()
            Database.allMovies.toMovies()
        }
    }

    private suspend inline fun <T : Any> shouldNotBeCancelled(
        crossinline operation: suspend (coroutineScope: CoroutineScope) -> T
    ): T {
        return withContext(dispatcher) {
            scopeThatShouldNotBeCancelled.async {
                operation(this)
            }.await()
        }
    }
}