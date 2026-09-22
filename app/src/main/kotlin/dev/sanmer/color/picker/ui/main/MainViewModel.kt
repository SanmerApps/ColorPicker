package dev.sanmer.color.picker.ui.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.derivedStateOf
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
    private var lightColorScheme by mutableStateOf(lightColorScheme())
    private var darkColorScheme by mutableStateOf(darkColorScheme())

    val lightColors by derivedStateOf { ColorSchemeCompat(lightColorScheme) }
    val darkColors by derivedStateOf { ColorSchemeCompat(darkColorScheme) }

    var colorValue by mutableStateOf(ColorValue.RGB)

    var bottomSheet by mutableStateOf<BottomSheet>(BottomSheet.None)

    fun reload(context: Context) {
        lightColorScheme = dynamicLightColorScheme(context)
        darkColorScheme = dynamicDarkColorScheme(context)
    }

    fun colorScheme(darkTheme: Boolean) = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    fun importFromJson(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val stream = context.contentResolver.openInputStream(uri) ?: return@launch
                val colorJson = stream.use { input -> ColorJson.decodeFrom(colorValue, input) }
                lightColorScheme = colorJson.light
                darkColorScheme = colorJson.dark
            }.onFailure {
                Log.e(TAG, "importFromJson", it)
            }
        }
    }

    fun exportToJson(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val stream = context.contentResolver.openOutputStream(uri) ?: return@launch
                val colorJson = ColorJson(light = lightColorScheme, dark = darkColorScheme)
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
                val colorKt = ColorKt(light = lightColorScheme, dark = darkColorScheme)
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