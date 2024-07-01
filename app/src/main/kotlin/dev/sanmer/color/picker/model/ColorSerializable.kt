package dev.sanmer.color.picker.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ColorSerializable(
    val value: ULong
) {
    companion object {
        val Color.serializer get() = ColorSerializable(value)
        val ColorSerializable.color get() = Color(value)
    }
}