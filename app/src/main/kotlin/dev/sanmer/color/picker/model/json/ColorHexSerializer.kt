package dev.sanmer.color.picker.model.json

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import dev.sanmer.color.picker.ktx.hexValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ColorHexSerializer : KSerializer<Color> {
    override val descriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Color",
        original = serialDescriptor<String>()
    )

    override fun serialize(encoder: Encoder, value: Color) {
        val rgb = value.convert(ColorSpaces.Srgb)
        encoder.encodeString(rgb.hexValue)
    }

    override fun deserialize(decoder: Decoder): Color {
        val value = decoder.decodeString()
        return Color(value.toLong(16))
    }
}