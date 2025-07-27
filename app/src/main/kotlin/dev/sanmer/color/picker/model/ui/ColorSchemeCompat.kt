package dev.sanmer.color.picker.model.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

data class ColorSchemeCompat(
    val colorScheme: ColorScheme
) {
    val primary by lazy {
        listOf(
            ColorCompat("Primary", colorScheme.primary, colorScheme.onPrimary),
            ColorCompat("On Primary", colorScheme.onPrimary, colorScheme.primary),
            ColorCompat("Primary Container", colorScheme.primaryContainer, colorScheme.onPrimaryContainer),
            ColorCompat("On Primary Container", colorScheme.onPrimaryContainer, colorScheme.primaryContainer)
        )
    }

    val secondary by lazy {
        listOf(
            ColorCompat("Secondary", colorScheme.secondary, colorScheme.onSecondary),
            ColorCompat("On Secondary", colorScheme.onSecondary, colorScheme.secondary),
            ColorCompat("Secondary Container", colorScheme.secondaryContainer, colorScheme.onSecondaryContainer),
            ColorCompat("On Secondary Container", colorScheme.onSecondaryContainer, colorScheme.secondaryContainer)
        )
    }

    val tertiary by lazy {
        listOf(
            ColorCompat("Tertiary", colorScheme.tertiary, colorScheme.onTertiary),
            ColorCompat("On Tertiary", colorScheme.onTertiary, colorScheme.tertiary),
            ColorCompat("Tertiary Container", colorScheme.tertiaryContainer, colorScheme.onTertiaryContainer),
            ColorCompat("On Tertiary Container", colorScheme.onTertiaryContainer, colorScheme.tertiaryContainer)
        )
    }

    val error by lazy {
        listOf(
            ColorCompat("Error", colorScheme.error, colorScheme.onError),
            ColorCompat("On Error", colorScheme.onError, colorScheme.error),
            ColorCompat("Error Container", colorScheme.errorContainer, colorScheme.onErrorContainer),
            ColorCompat("On Error Container", colorScheme.onErrorContainer, colorScheme.errorContainer)
        )
    }

    val surface by lazy {
        listOf(
            ColorCompat("Surface Dim", colorScheme.surfaceDim, colorScheme.onSurface),
            ColorCompat("Surface", colorScheme.surface, colorScheme.onSurface),
            ColorCompat("Surface Bright", colorScheme.surfaceBright, colorScheme.onSurface),
            ColorCompat("Surface Container Lowest", colorScheme.surfaceContainerLowest, colorScheme.onSurfaceVariant),
            ColorCompat("Surface Container Low", colorScheme.surfaceContainerLow, colorScheme.onSurfaceVariant),
            ColorCompat("Surface Container", colorScheme.surfaceContainer, colorScheme.onSurfaceVariant),
            ColorCompat("Surface Container High", colorScheme.surfaceContainerHigh, colorScheme.onSurfaceVariant),
            ColorCompat("Surface Container Highest", colorScheme.surfaceContainerHighest, colorScheme.onSurfaceVariant),
            ColorCompat("On Surface", colorScheme.onSurface, colorScheme.surface),
            ColorCompat("On Surface Variant", colorScheme.onSurfaceVariant, colorScheme.surfaceVariant),
            ColorCompat("Outline", colorScheme.outline, colorScheme.surface),
            ColorCompat("Outline Variant", colorScheme.outlineVariant, colorScheme.onSurface)
        )
    }

    val other by lazy {
        listOf(
            ColorCompat("Inverse Surface", colorScheme.inverseSurface, colorScheme.surface),
            ColorCompat("Inverse On Surface", colorScheme.inverseOnSurface, colorScheme.onSurface),
            ColorCompat("Inverse Primary", colorScheme.inversePrimary, colorScheme.primary),
            ColorCompat("Surface Tint", colorScheme.surfaceTint, colorScheme.surface),
            ColorCompat("Background", colorScheme.background, colorScheme.onBackground),
            ColorCompat("Scrim", colorScheme.scrim, Color.White)
        )
    }
}