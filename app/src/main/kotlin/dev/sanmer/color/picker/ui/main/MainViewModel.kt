package dev.sanmer.color.picker.ui.main

import android.content.Context
import android.net.Uri
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanmer.color.picker.Logger
import dev.sanmer.color.picker.annotation.ApplicationContext
import dev.sanmer.color.picker.model.ColorJson
import dev.sanmer.color.picker.model.ColorKt
import dev.sanmer.color.picker.model.ColorSchemeCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val dynamicLightColorScheme get() = dynamicLightColorScheme(context)
    private val dynamicDarkColorScheme get() = dynamicDarkColorScheme(context)

    private var lightColorScheme by mutableStateOf(dynamicLightColorScheme)
    private var darkColorScheme by mutableStateOf(dynamicDarkColorScheme)

    val lightColors by derivedStateOf { ColorSchemeCompat(lightColorScheme) }
    val darkColors by derivedStateOf { ColorSchemeCompat(darkColorScheme) }

    private val logger = Logger.Android("MainViewModel")

    fun reload() {
        lightColorScheme = dynamicLightColorScheme
        darkColorScheme = dynamicDarkColorScheme
    }

    fun colorScheme(darkTheme: Boolean) = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    fun importFromJson(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                checkNotNull(context.contentResolver.openInputStream(uri)).use(
                    ColorJson.Default::decodeFrom
                )
            }.onSuccess {
                lightColorScheme = it.light.colorScheme
                darkColorScheme = it.dark.colorScheme

            }.onFailure {
                logger.e(it)
            }
        }
    }

    fun exportToJson(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val colorJson = ColorJson(
                    light = lightColorScheme,
                    dark = darkColorScheme
                )

                checkNotNull(context.contentResolver.openOutputStream(uri)).use(
                    colorJson::encodeTo
                )
            }.onFailure {
                logger.e(it)
            }
        }
    }

    fun exportToKotlin(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val colorKt = ColorKt(
                    light = lightColorScheme,
                    dark = darkColorScheme
                )

                checkNotNull(context.contentResolver.openOutputStream(uri)).use(
                    colorKt::encodeTo
                )
            }.onFailure {
                logger.e(it)
            }
        }
    }
}