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

    val primaryFixed by lazy {
        listOf(
            ColorCompat("Primary Fixed", colorScheme.primaryFixed, colorScheme.onPrimaryFixed),
            ColorCompat("Primary Fixed Dim", colorScheme.primaryFixedDim, colorScheme.onPrimaryFixed),
            ColorCompat("On Primary Fixed", colorScheme.onPrimaryFixed, colorScheme.primaryFixed),
            ColorCompat("On Primary Fixed Variant", colorScheme.onPrimaryFixedVariant, colorScheme.primaryFixed)
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

    val secondaryFixed by lazy {
        listOf(
            ColorCompat("Secondary Fixed", colorScheme.secondaryFixed, colorScheme.onSecondaryFixed),
            ColorCompat("Secondary Fixed Dim", colorScheme.secondaryFixedDim, colorScheme.onSecondaryFixed),
            ColorCompat("On Secondary Fixed", colorScheme.onSecondaryFixed, colorScheme.secondaryFixed),
            ColorCompat("On Secondary Fixed Variant", colorScheme.onSecondaryFixedVariant, colorScheme.secondaryFixed)
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

    val tertiaryFixed by lazy {
        listOf(
            ColorCompat("Tertiary Fixed", colorScheme.tertiaryFixed, colorScheme.onTertiaryFixed),
            ColorCompat("Tertiary Fixed Dim", colorScheme.tertiaryFixedDim, colorScheme.onTertiaryFixed),
            ColorCompat("On Tertiary Fixed", colorScheme.onTertiaryFixed, colorScheme.tertiaryFixed),
            ColorCompat("On Tertiary Fixed Variant", colorScheme.onTertiaryFixedVariant, colorScheme.tertiaryFixed)
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
            ColorCompat("Surface Container Lowest", colorScheme.surfaceContainerLowest, colorScheme.onSurface),
            ColorCompat("Surface Container Low", colorScheme.surfaceContainerLow, colorScheme.onSurface),
            ColorCompat("Surface Container", colorScheme.surfaceContainer, colorScheme.onSurface),
            ColorCompat("Surface Container High", colorScheme.surfaceContainerHigh, colorScheme.onSurface),
            ColorCompat("Surface Container Highest", colorScheme.surfaceContainerHighest, colorScheme.onSurface),
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