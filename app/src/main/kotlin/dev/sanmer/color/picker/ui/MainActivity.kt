package dev.sanmer.color.picker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.sanmer.color.picker.ui.main.MainScreen
import dev.sanmer.color.picker.ui.theme.AppTheme
import dev.sanmer.color.picker.viewmodel.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

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