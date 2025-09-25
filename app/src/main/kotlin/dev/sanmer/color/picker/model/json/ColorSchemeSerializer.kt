package dev.sanmer.color.picker.model.json

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

object ColorSchemeSerializer : KSerializer<ColorScheme> {
    override val descriptor = SerialDescriptor(
        serialName = "androidx.compose.material3.ColorScheme",
        original = serialDescriptor<Wrapper>()
    )

    override fun serialize(
        encoder: Encoder,
        value: ColorScheme
    ) {
        encoder.encodeSerializableValue(
            serializer = serializer<Wrapper>(),
            value = Wrapper(value)
        )
    }

    override fun deserialize(decoder: Decoder): ColorScheme {
        return decoder.decodeSerializableValue(
            deserializer = serializer<Wrapper>()
        ).colorScheme
    }

    @Serializable
    data class Wrapper(
        @Contextual val primary: Color,
        @Contextual val onPrimary: Color,
        @Contextual val primaryContainer: Color,
        @Contextual val onPrimaryContainer: Color,
        @Contextual val inversePrimary: Color,
        @Contextual val secondary: Color,
        @Contextual val onSecondary: Color,
        @Contextual val secondaryContainer: Color,
        @Contextual val onSecondaryContainer: Color,
        @Contextual val tertiary: Color,
        @Contextual val onTertiary: Color,
        @Contextual val tertiaryContainer: Color,
        @Contextual val onTertiaryContainer: Color,
        @Contextual val background: Color,
        @Contextual val onBackground: Color,
        @Contextual val surface: Color,
        @Contextual val onSurface: Color,
        @Contextual val surfaceVariant: Color,
        @Contextual val onSurfaceVariant: Color,
        @Contextual val surfaceTint: Color,
        @Contextual val inverseSurface: Color,
        @Contextual val inverseOnSurface: Color,
        @Contextual val error: Color,
        @Contextual val onError: Color,
        @Contextual val errorContainer: Color,
        @Contextual val onErrorContainer: Color,
        @Contextual val outline: Color,
        @Contextual val outlineVariant: Color,
        @Contextual val scrim: Color,
        @Contextual val surfaceBright: Color,
        @Contextual val surfaceDim: Color,
        @Contextual val surfaceContainer: Color,
        @Contextual val surfaceContainerHigh: Color,
        @Contextual val surfaceContainerHighest: Color,
        @Contextual val surfaceContainerLow: Color,
        @Contextual val surfaceContainerLowest: Color,
        @Contextual val primaryFixed: Color,
        @Contextual val primaryFixedDim: Color,
        @Contextual val onPrimaryFixed: Color,
        @Contextual val onPrimaryFixedVariant: Color,
        @Contextual val secondaryFixed: Color,
        @Contextual val secondaryFixedDim: Color,
        @Contextual val onSecondaryFixed: Color,
        @Contextual val onSecondaryFixedVariant: Color,
        @Contextual val tertiaryFixed: Color,
        @Contextual val tertiaryFixedDim: Color,
        @Contextual val onTertiaryFixed: Color,
        @Contextual val onTertiaryFixedVariant: Color
    ) {
        constructor(colorScheme: ColorScheme) : this(
            primary = colorScheme.primary,
            onPrimary = colorScheme.onPrimary,
            primaryContainer = colorScheme.primaryContainer,
            onPrimaryContainer = colorScheme.onPrimaryContainer,
            inversePrimary = colorScheme.inversePrimary,
            secondary = colorScheme.secondary,
            onSecondary = colorScheme.onSecondary,
            secondaryContainer = colorScheme.secondaryContainer,
            onSecondaryContainer = colorScheme.onSecondaryContainer,
            tertiary = colorScheme.tertiary,
            onTertiary = colorScheme.onTertiary,
            tertiaryContainer = colorScheme.tertiaryContainer,
            onTertiaryContainer = colorScheme.onTertiaryContainer,
            background = colorScheme.background,
            onBackground = colorScheme.onBackground,
            surface = colorScheme.surface,
            onSurface = colorScheme.onSurface,
            surfaceVariant = colorScheme.surfaceVariant,
            onSurfaceVariant = colorScheme.onSurfaceVariant,
            surfaceTint = colorScheme.surfaceTint,
            inverseSurface = colorScheme.inverseSurface,
            inverseOnSurface = colorScheme.inverseOnSurface,
            error = colorScheme.error,
            onError = colorScheme.onError,
            errorContainer = colorScheme.errorContainer,
            onErrorContainer = colorScheme.onErrorContainer,
            outline = colorScheme.outline,
            outlineVariant = colorScheme.outlineVariant,
            scrim = colorScheme.scrim,
            surfaceBright = colorScheme.surfaceBright,
            surfaceDim = colorScheme.surfaceDim,
            surfaceContainer = colorScheme.surfaceContainer,
            surfaceContainerHigh = colorScheme.surfaceContainerHigh,
            surfaceContainerHighest = colorScheme.surfaceContainerHighest,
            surfaceContainerLow = colorScheme.surfaceContainerLow,
            surfaceContainerLowest = colorScheme.surfaceContainerLowest,
            primaryFixed = colorScheme.primaryFixed,
            primaryFixedDim = colorScheme.primaryFixedDim,
            onPrimaryFixed = colorScheme.onPrimaryFixed,
            onPrimaryFixedVariant = colorScheme.onPrimaryFixedVariant,
            secondaryFixed = colorScheme.secondaryFixed,
            secondaryFixedDim = colorScheme.secondaryFixedDim,
            onSecondaryFixed = colorScheme.onSecondaryFixed,
            onSecondaryFixedVariant = colorScheme.onSecondaryFixedVariant,
            tertiaryFixed = colorScheme.tertiaryFixed,
            tertiaryFixedDim = colorScheme.tertiaryFixedDim,
            onTertiaryFixed = colorScheme.onTertiaryFixed,
            onTertiaryFixedVariant = colorScheme.onTertiaryFixedVariant
        )

        val colorScheme
            get() = ColorScheme(
                primary = primary,
                onPrimary = onPrimary,
                primaryContainer = primaryContainer,
                onPrimaryContainer = onPrimaryContainer,
                inversePrimary = inversePrimary,
                secondary = secondary,
                onSecondary = onSecondary,
                secondaryContainer = secondaryContainer,
                onSecondaryContainer = onSecondaryContainer,
                tertiary = tertiary,
                onTertiary = onTertiary,
                tertiaryContainer = tertiaryContainer,
                onTertiaryContainer = onTertiaryContainer,
                background = background,
                onBackground = onBackground,
                surface = surface,
                onSurface = onSurface,
                surfaceVariant = surfaceVariant,
                onSurfaceVariant = onSurfaceVariant,
                surfaceTint = surfaceTint,
                inverseSurface = inverseSurface,
                inverseOnSurface = inverseOnSurface,
                error = error,
                onError = onError,
                errorContainer = errorContainer,
                onErrorContainer = onErrorContainer,
                outline = outline,
                outlineVariant = outlineVariant,
                scrim = scrim,
                surfaceBright = surfaceBright,
                surfaceDim = surfaceDim,
                surfaceContainer = surfaceContainer,
                surfaceContainerHigh = surfaceContainerHigh,
                surfaceContainerHighest = surfaceContainerHighest,
                surfaceContainerLow = surfaceContainerLow,
                surfaceContainerLowest = surfaceContainerLowest,
                primaryFixed = primaryFixed,
                primaryFixedDim = primaryFixedDim,
                onPrimaryFixed = onPrimaryFixed,
                onPrimaryFixedVariant = onPrimaryFixedVariant,
                secondaryFixed = secondaryFixed,
                secondaryFixedDim = secondaryFixedDim,
                onSecondaryFixed = onSecondaryFixed,
                onSecondaryFixedVariant = onSecondaryFixedVariant,
                tertiaryFixed = tertiaryFixed,
                tertiaryFixedDim = tertiaryFixedDim,
                onTertiaryFixed = onTertiaryFixed,
                onTertiaryFixedVariant = onTertiaryFixedVariant
            )
    }
}