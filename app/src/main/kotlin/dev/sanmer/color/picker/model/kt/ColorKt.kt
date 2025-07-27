package dev.sanmer.color.picker.model.kt

import androidx.compose.material3.ColorScheme
import com.squareup.kotlinpoet.FileSpec
import dev.sanmer.color.picker.model.ColorValue
import java.io.OutputStream

data class ColorKt(
    val light: ColorScheme,
    val dark: ColorScheme
) {
    fun encodeTo(value: ColorValue, output: OutputStream) = output.bufferedWriter().use { writer ->
        val lightColorScheme by ColorSchemeProperty(
            name = "LightColorScheme",
            colorScheme = light,
            value = value
        )

        val darkColorScheme by ColorSchemeProperty(
            name = "DarkColorScheme",
            colorScheme = dark,
            value = value
        )

        val content = FileSpec.builder("", "")
            .addProperty(lightColorScheme)
            .addProperty(darkColorScheme)
            .build()
            .toString()

        writer.write(content)
    }

    companion object Default {
        const val MIME_TYPE = "text/x-kotlin"
        const val FILE_NAME = "Color.kt"
    }
}