package dev.sanmer.color.picker.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.withIndent
import java.io.OutputStream

data class ColorKt(
    val light: ColorScheme,
    val dark: ColorScheme
) {
    private val Color.hexValue get() = value.toString(16).substring(0, 8).uppercase()

    private fun CodeBlock.Builder.color(name: String, color: Color, end: String = ",\n") {
        add(CodeBlock.of("%L = %T(0x%L)", name, ColorClass, color.hexValue))
        add(end)
    }

    private fun colorScheme(colorScheme: ColorScheme) = buildCodeBlock {
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

    fun encodeTo(output: OutputStream) = output.bufferedWriter().use { writer ->
        val content = FileSpec.builder("", "")
            .addProperty(
                PropertySpec.builder("LightColorScheme", ColorSchemeClass)
                    .addModifiers(KModifier.PUBLIC)
                    .initializer(colorScheme(light))
                    .build()
            )
            .addProperty(
                PropertySpec.builder("DarkColorScheme", ColorSchemeClass)
                    .addModifiers(KModifier.PUBLIC)
                    .initializer(colorScheme(dark))
                    .build()
            )
            .build()
            .toString()

        writer.write(content)
    }

    companion object {
        private val ColorSchemeClass by lazy {
            ClassName("androidx.compose.material3", "ColorScheme")
        }
        private val ColorClass by lazy {
            ClassName("androidx.compose.ui.graphics", "Color")
        }

        const val FILE_NAME = "Color.kt"
    }
}