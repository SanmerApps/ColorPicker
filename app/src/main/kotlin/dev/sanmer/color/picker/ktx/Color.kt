package dev.sanmer.color.picker.ktx

import androidx.compose.ui.graphics.Color

inline val Color.hexValue get() = value.toString(16).substring(0, 8).uppercase()

inline val Color.redValue get() = ((value shr 48) and 0xffUL).toInt()
inline val Color.greenValue get() = ((value shr 40) and 0xffUL).toInt()
inline val Color.blueValue get() = ((value shr 32) and 0xffUL).toInt()
inline val Color.alphaValue get() = ((value shr 56) and 0xffUL).toInt()