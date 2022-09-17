package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.televiziacompose.database.Database
import com.hotmail.or_dvir.televiziacompose.database.toMovieModels
import com.hotmail.or_dvir.televiziacompose.models.MovieModel
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

    override suspend fun getAllMovies(): List<MovieModel> {
        return withContext(dispatcher) {
            pretendToLoad()
            Database.allMovies.toMovieModels()
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