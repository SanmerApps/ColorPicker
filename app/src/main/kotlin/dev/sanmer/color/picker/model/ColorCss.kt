package dev.sanmer.color.picker.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import java.io.OutputStream
import kotlin.text.appendLine

data class ColorCss(
    val light: ColorScheme,
    val dark: ColorScheme,
) {
    private val Color.hexValue get(): String = "#${red.hex}${green.hex}${blue.hex}${alpha.hex}"

    private val Float.hex
        get(): String {
            return (this * 255).toInt().coerceIn(0, 255).toString(16).padStart(2, '0')
        }

    private fun StringBuilder.color(name: String, color: Color, end: String = ",\n") {
        append("\t$name: ${color.hexValue}")
        append(end)
    }

    private fun colorScheme(name: String = "root", colorScheme: ColorScheme) = buildString {
        append(":$name {\n")
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
        append("\n}")
    }

    fun encodeTo(output: OutputStream) = output.bufferedWriter().use { writer ->
        val content = buildString {
            appendLine(colorScheme("lightColorScheme", light))
            append("\n")
            appendLine(colorScheme("darkColorScheme", dark))
        }

        writer.write(content)
    }

    companion object Default {
        const val MIME_TYPE = "text/css"
        const val FILE_NAME = "color.css"
    }
}