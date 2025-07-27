package dev.sanmer.color.picker.model.json

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import dev.sanmer.color.picker.ktx.alphaValue
import dev.sanmer.color.picker.ktx.blueValue
import dev.sanmer.color.picker.ktx.greenValue
import dev.sanmer.color.picker.ktx.redValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

object ColorRgbSerializer : KSerializer<Color> {
    override val descriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Color",
        original = serialDescriptor<Wrapper>()
    )

    override fun serialize(encoder: Encoder, value: Color) {
        encoder.encodeSerializableValue(
            serializer = serializer<Wrapper>(),
            value = Wrapper(value.convert(ColorSpaces.Srgb))
        )
    }

    override fun deserialize(decoder: Decoder): Color {
        return decoder.decodeSerializableValue(
            deserializer = serializer<Wrapper>()
        ).color
    }

    @Serializable
    data class Wrapper(
        val red: Int,
        val green: Int,
        val blue: Int,
        val alpha: Int
    ) {
        constructor(color: Color) : this(
            red = color.redValue,
            green = color.greenValue,
            blue = color.blueValue,
            alpha = color.alphaValue
        )

        val color get() = Color(red, green, blue, alpha)
    }
}