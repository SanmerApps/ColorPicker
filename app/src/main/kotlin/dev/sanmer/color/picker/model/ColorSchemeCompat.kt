package dev.sanmer.color.picker.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color as ComposeColor

data class ColorSchemeCompat(
    val colorScheme: ColorScheme,
) {
    val primary by lazy {
        listOf(
            Color("Primary", colorScheme.primary, colorScheme.onPrimary),
            Color("On Primary", colorScheme.onPrimary, colorScheme.primary),
            Color("Primary Container", colorScheme.primaryContainer, colorScheme.onPrimaryContainer),
            Color("On Primary Container", colorScheme.onPrimaryContainer, colorScheme.primaryContainer),
        )
    }

    val secondary by lazy {
        listOf(
            Color("Secondary", colorScheme.secondary, colorScheme.onSecondary),
            Color("On Secondary", colorScheme.onSecondary, colorScheme.secondary),
            Color("Secondary Container", colorScheme.secondaryContainer, colorScheme.onSecondaryContainer),
            Color("On Secondary Container", colorScheme.onSecondaryContainer, colorScheme.secondaryContainer),
        )
    }

    val tertiary by lazy {
        listOf(
            Color("Tertiary", colorScheme.tertiary, colorScheme.onTertiary),
            Color("On Tertiary", colorScheme.onTertiary, colorScheme.tertiary),
            Color("Tertiary Container", colorScheme.tertiaryContainer, colorScheme.onTertiaryContainer),
            Color("On Tertiary Container", colorScheme.onTertiaryContainer, colorScheme.tertiaryContainer),
        )
    }

    val error by lazy {
        listOf(
            Color("Error", colorScheme.error, colorScheme.onError),
            Color("On Error", colorScheme.onError, colorScheme.error),
            Color("Error Container", colorScheme.errorContainer, colorScheme.onErrorContainer),
            Color("On Error Container", colorScheme.onErrorContainer, colorScheme.errorContainer),
        )
    }

    val surface by lazy {
        listOf(
            Color("Surface Dim", colorScheme.surfaceDim, colorScheme.onSurface),
            Color("Surface", colorScheme.surface, colorScheme.onSurface),
            Color("Surface Bright", colorScheme.surfaceBright, colorScheme.onSurface),
            Color("Surface Container Lowest", colorScheme.surfaceContainerLowest, colorScheme.onSurfaceVariant),
            Color("Surface Container Low", colorScheme.surfaceContainerLow, colorScheme.onSurfaceVariant),
            Color("Surface Container", colorScheme.surfaceContainer, colorScheme.onSurfaceVariant),
            Color("Surface Container High", colorScheme.surfaceContainerHigh, colorScheme.onSurfaceVariant),
            Color("Surface Container Highest", colorScheme.surfaceContainerHighest, colorScheme.onSurfaceVariant),
            Color("On Surface", colorScheme.onSurface, colorScheme.surface),
            Color("On Surface Variant", colorScheme.onSurfaceVariant, colorScheme.surfaceVariant),
            Color("Outline", colorScheme.outline, colorScheme.surface),
            Color("Outline Variant", colorScheme.outlineVariant, colorScheme.onSurface),
        )
    }

    val other by lazy {
        listOf(
            Color("Inverse Surface", colorScheme.inverseSurface, colorScheme.surface),
            Color("Inverse On Surface", colorScheme.inverseOnSurface, colorScheme.onSurface),
            Color("Inverse Primary", colorScheme.inversePrimary, colorScheme.primary),
            Color("Surface Tint", colorScheme.surfaceTint, colorScheme.surface),
            Color("Background", colorScheme.background, colorScheme.onBackground),
            Color("Scrim", colorScheme.scrim, ComposeColor.White),
        )
    }

    data class Color(
        val name: String,
        val containerColor: ComposeColor,
        val contentColor: ComposeColor
    )
}