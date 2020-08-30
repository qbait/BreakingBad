package io.digitalheart.breakingbad.core

import android.app.Application
import io.digitalheart.breakingbad.di.appModule
import io.digitalheart.breakingbad.di.networkModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, networkModule))
        Timber.plant(Timber.DebugTree())
    }
}