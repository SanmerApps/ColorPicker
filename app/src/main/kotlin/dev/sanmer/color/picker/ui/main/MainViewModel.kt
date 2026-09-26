package dev.sanmer.color.picker.ui.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanmer.color.picker.model.ColorValue
import dev.sanmer.color.picker.model.json.ColorJson
import dev.sanmer.color.picker.model.kt.ColorKt
import dev.sanmer.color.picker.model.ui.ColorCompat
import dev.sanmer.color.picker.model.ui.ColorSchemeCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var lightColors by mutableStateOf(ColorSchemeCompat(lightColorScheme()))
        private set

    var darkColors by mutableStateOf(ColorSchemeCompat(darkColorScheme()))
        private set

    var colorValue by mutableStateOf(ColorValue.RGB)

    var bottomSheet by mutableStateOf<BottomSheet>(BottomSheet.None)

    fun reload(context: Context) {
        lightColors = ColorSchemeCompat(dynamicLightColorScheme(context))
        darkColors = ColorSchemeCompat(dynamicDarkColorScheme(context))
    }

    fun colorScheme(darkTheme: Boolean) = when {
        darkTheme -> darkColors.colorScheme
        else -> lightColors.colorScheme
    }

    fun importFromJson(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val stream = context.contentResolver.openInputStream(uri) ?: return@launch
                val colorJson = stream.use { input -> ColorJson.decodeFrom(colorValue, input) }
                lightColors = ColorSchemeCompat(colorJson.light)
                darkColors = ColorSchemeCompat(colorJson.dark)
            }.onFailure {
                Log.e(TAG, "importFromJson", it)
            }
        }
    }

    fun exportToJson(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val stream = context.contentResolver.openOutputStream(uri) ?: return@launch
                val colorJson = ColorJson(
                    light = lightColors.colorScheme,
                    dark = darkColors.colorScheme
                )
                stream.use { output -> colorJson.encodeTo(colorValue, output) }
            }.onFailure {
                Log.e(TAG, "exportToJson", it)
            }
        }
    }

    fun exportToKotlin(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val stream = context.contentResolver.openOutputStream(uri) ?: return@launch
                val colorKt = ColorKt(
                    light = lightColors.colorScheme,
                    dark = darkColors.colorScheme
                )
                stream.use { output -> colorKt.encodeTo(colorValue, output) }
            }.onFailure {
                Log.e(TAG, "exportToKotlin", it)
            }
        }
    }

    sealed interface BottomSheet {
        data object None : BottomSheet
        data class Color(val color: ColorCompat) : BottomSheet
    }

    private companion object Default {
        const val TAG = "MainViewModel"
    }
}