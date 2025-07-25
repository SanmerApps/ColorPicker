package dev.sanmer.color.picker

import android.app.Application
import dev.sanmer.color.picker.di.ViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(ViewModels)
        }
    }
}