package dev.sanmer.color.picker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    colorScheme: (Boolean) -> ColorScheme,
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = colorScheme(darkMode),
    shapes = Shapes,
    typography = Typography,
    content = content
)