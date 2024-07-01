package dev.sanmer.color.picker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.sanmer.color.picker.utils.timber.DebugTree
import dev.sanmer.color.picker.utils.timber.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}