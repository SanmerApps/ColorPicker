package dev.sanmer.color.picker.model.kt

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.withIndent
import dev.sanmer.color.picker.ktx.alphaValue
import dev.sanmer.color.picker.ktx.blueValue
import dev.sanmer.color.picker.ktx.greenValue
import dev.sanmer.color.picker.ktx.hexValue
import dev.sanmer.color.picker.ktx.redValue
import dev.sanmer.color.picker.model.ColorValue
import kotlin.reflect.KProperty

class ColorSchemeProperty(
    private val name: String,
    private val colorScheme: ColorScheme,
    private val value: ColorValue
) {
    private fun CodeBlock.Builder.color(
        name: String,
        color: Color,
        end: String = ",\n"
    ) {
        val rgb = color.convert(ColorSpaces.Srgb)
        add(
            when (value) {
                ColorValue.RGB -> CodeBlock.of(
                    "%L = %T(%L, %L, %L, %L)",
                    name,
                    ColorClass,
                    rgb.redValue,
                    rgb.greenValue,
                    rgb.blueValue,
                    rgb.alphaValue
                )

                ColorValue.HEX -> CodeBlock.of("%L = %T(0x%L)", name, ColorClass, rgb.hexValue)
            }
        )
        add(end)
    }

    private val initializer
        get() = buildCodeBlock {
            addStatement("%T(", ColorSchemeClass)
            withIndent {
                color("primary", colorScheme.primary)
                color("onPrimary", colorScheme.onPrimary)
                color("primaryContainer", colorScheme.primaryContainer)
                color("onPrimaryContainer", colorScheme.onPrimaryContainer)
                color("inversePrimary", colorScheme.inversePrimary)
                color("secondary", colorScheme.secondary)
                color("onSecondary", colorScheme.onSecondary)
                color("secondaryContainer", colorScheme.secondaryContainer)
                color("onSecondaryContainer", colorScheme.onSecondaryContainer)
                color("tertiary", colorScheme.tertiary)
                color("onTertiary", colorScheme.onTertiary)
                color("tertiaryContainer", colorScheme.tertiaryContainer)
                color("onTertiaryContainer", colorScheme.onTertiaryContainer)
                color("background", colorScheme.background)
                color("onBackground", colorScheme.onBackground)
                color("surface", colorScheme.surface)
                color("onSurface", colorScheme.onSurface)
                color("surfaceVariant", colorScheme.surfaceVariant)
                color("onSurfaceVariant", colorScheme.onSurfaceVariant)
                color("surfaceTint", colorScheme.surfaceTint)
                color("inverseSurface", colorScheme.inverseSurface)
                color("inverseOnSurface", colorScheme.inverseOnSurface)
                color("error", colorScheme.error)
                color("onError", colorScheme.onError)
                color("errorContainer", colorScheme.errorContainer)
                color("onErrorContainer", colorScheme.onErrorContainer)
                color("outline", colorScheme.outline)
                color("outlineVariant", colorScheme.outlineVariant)
                color("scrim", colorScheme.scrim)
                color("surfaceBright", colorScheme.surfaceBright)
                color("surfaceDim", colorScheme.surfaceDim)
                color("surfaceContainer", colorScheme.surfaceContainer)
                color("surfaceContainerHigh", colorScheme.surfaceContainerHigh)
                color("surfaceContainerHighest", colorScheme.surfaceContainerHighest)
                color("surfaceContainerLow", colorScheme.surfaceContainerLow)
                color("surfaceContainerLowest", colorScheme.surfaceContainerLowest, "")
            }
            add("\n)")
        }

    operator fun getValue(thisObj: Any?, property: KProperty<*>) =
        PropertySpec.builder(name, ColorSchemeClass)
            .addModifiers(KModifier.PUBLIC)
            .initializer(initializer)
            .build()

    private companion object Default {
        val ColorSchemeClass by lazy {
            ClassName("androidx.compose.material3", "ColorScheme")
        }

        val ColorClass by lazy {
            ClassName("androidx.compose.ui.graphics", "Color")
        }
    }
}