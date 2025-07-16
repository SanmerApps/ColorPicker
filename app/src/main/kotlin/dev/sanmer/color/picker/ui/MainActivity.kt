package dev.sanmer.color.picker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.sanmer.color.picker.ui.main.MainScreen
import dev.sanmer.color.picker.ui.main.MainViewModel
import dev.sanmer.color.picker.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SideEffect(viewModel::reload)

            AppTheme(
                colorScheme = viewModel::colorScheme
            ) {
                MainScreen()
            }
        }
    }
}