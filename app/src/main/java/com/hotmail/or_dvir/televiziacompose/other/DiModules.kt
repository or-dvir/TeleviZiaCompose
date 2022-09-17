package com.hotmail.or_dvir.televiziacompose.other

import com.hotmail.or_dvir.televiziacompose.database.Database
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepository
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepositoryImpl
import com.hotmail.or_dvir.televiziacompose.ui.all_movies.AllMoviesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DiModules {
    fun getModules() = listOf(
        viewModels,
        database,
        repositories
    )

    private val viewModels = module {
        viewModel { AllMoviesViewModel(get()) }
//        viewModel { (listId: UUID) -> ListItemsViewModel(listId, get(), androidApplication()) }
//        viewModel { (listId: UUID?) -> NewEditListViewModel(listId, get(), androidApplication()) }
//        viewModel { (itemId: UUID?, listId: UUID) ->
//            NewEditItemViewModel(itemId, listId, get(), androidApplication())
//        }
    }

    private val database = module {
        single { Database }
    }

    private val repositories = module {
        val ioDispatcher = Dispatchers.IO

        single<MoviesRepository> {
            val appScope = (androidApplication() as MyApplication).scopeThatShouldNotBeCancelled
            MoviesRepositoryImpl(appScope, ioDispatcher)
        }
    }
}