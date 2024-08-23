package dev.sanmer.color.picker.model

import androidx.compose.material3.ColorScheme
import dev.sanmer.color.picker.model.ColorSchemeSerializable.Default.serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

@Serializable
class ColorJson(
    val light: ColorSchemeSerializable,
    val dark: ColorSchemeSerializable
) {
    constructor(
        light: ColorScheme,
        dark: ColorScheme
    ) : this(
        light = light.serializer,
        dark = dark.serializer
    )

    fun encodeTo(output: OutputStream) {
        endpointJson.encodeToStream(this, output)
    }

    companion object Default {
        private val endpointJson = Json {
            prettyPrint = true
        }

        const val MIME_TYPE = "application/json"
        const val FILE_NAME = "color.json"

        fun decodeFrom(input: InputStream): ColorJson =
            Json.decodeFromStream(input)
    }
}