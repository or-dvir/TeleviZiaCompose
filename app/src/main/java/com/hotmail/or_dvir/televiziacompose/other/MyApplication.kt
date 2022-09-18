package com.hotmail.or_dvir.televiziacompose.other

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused")
class MyApplication : Application() {
    private val TAG = MyApplication::class.java.simpleName

    private val exceptionHandler = CoroutineExceptionHandler { context, t ->
        Log.d(TAG, "a non-cancellable coroutine with context \"$context\" failed.\n${t.message}")
        Log.e(TAG, t.message, t)
    }

    val scopeThatShouldNotBeCancelled = CoroutineScope(SupervisorJob() + exceptionHandler)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(DiModules.getModules())
        }
    }
}