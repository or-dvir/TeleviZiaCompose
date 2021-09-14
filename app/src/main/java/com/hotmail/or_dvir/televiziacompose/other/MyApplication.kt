package com.hotmail.or_dvir.televiziacompose.other

import android.app.Application
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepository
import com.hotmail.or_dvir.televiziacompose.repositories.MoviesRepositoryImpl
import com.hotmail.or_dvir.televiziacompose.repositories.UsersRepository
import com.hotmail.or_dvir.televiziacompose.repositories.UsersRepositoryImpl
import com.hotmail.or_dvir.televiziacompose.ui.login_register.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApplication : Application()
{
    private val appModule = module {
        viewModel { LoginViewModel(androidApplication()) }
        single<MoviesRepository> { MoviesRepositoryImpl() }
        single<UsersRepository> { UsersRepositoryImpl() }
    }

    override fun onCreate()
    {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}