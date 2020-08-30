package io.digitalheart.breakingbad.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    bean { androidApplication().resources }
}