package dev.sanmer.color.picker.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ColorSerializable(
    val value: ULong
) {
    val color get() = Color(value)

    companion object {
        val Color.serializer get() = ColorSerializable(value)
    }
}