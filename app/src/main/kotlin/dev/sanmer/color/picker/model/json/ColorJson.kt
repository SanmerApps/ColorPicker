package dev.sanmer.color.picker.model.json

import androidx.compose.material3.ColorScheme
import dev.sanmer.color.picker.model.ColorValue
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.serializersModuleOf
import java.io.InputStream
import java.io.OutputStream

@Serializable
class ColorJson(
    @Contextual
    val light: ColorScheme,
    @Contextual
    val dark: ColorScheme
) {
    fun encodeTo(value: ColorValue, output: OutputStream) {
        when (value) {
            ColorValue.RGB -> rgbJson
            ColorValue.HEX -> hexJson
        }.encodeToStream(this, output)
    }

    companion object Default {
        private val endpointJson = Json {
            ignoreUnknownKeys
            prettyPrint = true
            serializersModule = serializersModuleOf(ColorSchemeSerializer)
        }

        private val hexJson = Json(endpointJson) {
            serializersModule += serializersModuleOf(ColorHexSerializer)
        }

        private val rgbJson = Json(endpointJson) {
            serializersModule += serializersModuleOf(ColorRgbSerializer)
        }

        const val MIME_TYPE = "application/json"
        const val FILE_NAME = "color.json"

        fun decodeFrom(value: ColorValue, input: InputStream): ColorJson =
            when (value) {
                ColorValue.RGB -> rgbJson
                ColorValue.HEX -> hexJson
            }.decodeFromStream(input)
    }
}