package com.hotmail.or_dvir.televiziacompose.other

import android.app.Application
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepository
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

@Suppress("unused")
class MyApplication : Application() {
    private val appModule = module {
        single<MoviesRepository> { MoviesRepositoryImpl() }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}