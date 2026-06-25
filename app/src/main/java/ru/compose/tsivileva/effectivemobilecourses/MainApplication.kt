package ru.compose.tsivileva.effectivemobilecourses

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.compose.tsivileva.effectivemobilecourses.di.appModule
import ru.compose.tsivileva.effectivemobilecourses.home.di.homeModule

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)

            modules(
               listOf(
                   appModule,
                   homeModule
               )
            )
        }
    }
}