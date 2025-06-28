package dev.sanmer.color.picker.reflect

import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dergoogler.mmrl.webui.interfaces.WXInterface
import com.dergoogler.mmrl.webui.interfaces.WXOptions
import dev.sanmer.color.picker.model.ColorCss
import dev.sanmer.color.picker.model.ColorJson
import dev.sanmer.color.picker.model.ColorKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ColorPicker(wxOptions: WXOptions) : WXInterface(wxOptions) {
    override var name: String = "ColorPicker"

    private val dynamicLightColorScheme get() = dynamicLightColorScheme(context)
    private val dynamicDarkColorScheme get() = dynamicDarkColorScheme(context)

    private var lightColorScheme by mutableStateOf(dynamicLightColorScheme)
    private var darkColorScheme by mutableStateOf(dynamicDarkColorScheme)

    @JavascriptInterface
    fun reload() {
        lightColorScheme = dynamicLightColorScheme
        darkColorScheme = dynamicDarkColorScheme
    }

    private fun exportToJson(uri: Uri) {
        scope.launch(Dispatchers.IO) {
            runCatching {
                val colorJson = ColorJson(
                    light = lightColorScheme,
                    dark = darkColorScheme
                )

                checkNotNull(context.contentResolver.openOutputStream(uri)).use(
                    colorJson::encodeTo
                )
            }.onFailure {
                Timber.e(it)
                console.error(it)
            }
        }
    }

    private fun exportToKotlin(uri: Uri) {
        scope.launch(Dispatchers.IO) {
            runCatching {
                val colorKt = ColorKt(
                    light = lightColorScheme,
                    dark = darkColorScheme
                )

                checkNotNull(context.contentResolver.openOutputStream(uri)).use(
                    colorKt::encodeTo
                )
            }.onFailure {
                Timber.e(it)
                console.error(it)
            }
        }
    }

    private fun exportToCss(uri: Uri) {
        scope.launch(Dispatchers.IO) {
            runCatching {
                val colorCss = ColorCss(
                    light = lightColorScheme,
                    dark = darkColorScheme
                )

                checkNotNull(context.contentResolver.openOutputStream(uri)).use(
                    colorCss::encodeTo
                )
            }.onFailure {
                Timber.e(it)
                console.error(it)
            }
        }
    }

    private fun createDocument(type: Int, fileName: String, mimeType: String) {
        scope.launch(Dispatchers.IO) {
            val createFileIntent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                setType(mimeType)
                putExtra(Intent.EXTRA_TITLE, fileName)
            }

            withActivity {
                startActivityForResult(createFileIntent, type)
            }
        }
    }

    @JavascriptInterface
    fun exportToKotlin() {
        createDocument(
            type = RESULT_CODE_KOTLIN,
            fileName = ColorKt.FILE_NAME,
            mimeType = ColorKt.MIME_TYPE
        )
    }

    @JavascriptInterface
    fun exportToJson() {
        createDocument(
            type = RESULT_CODE_JSON,
            fileName = ColorJson.FILE_NAME,
            mimeType = ColorJson.MIME_TYPE
        )
    }

    @JavascriptInterface
    fun exportToCss() {
        createDocument(
            type = RESULT_CODE_CSS,
            fileName = ColorCss.FILE_NAME,
            mimeType = ColorCss.MIME_TYPE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val uri = data?.data ?: return

        when (requestCode) {
            RESULT_CODE_JSON -> exportToJson(uri)
            RESULT_CODE_KOTLIN -> exportToKotlin(uri)
            RESULT_CODE_CSS -> exportToCss(uri)
        }
    }

    private companion object {
        const val RESULT_CODE_JSON: Int = 10191514
        const val RESULT_CODE_KOTLIN: Int = 111522914
        const val RESULT_CODE_CSS: Int = 3001919
    }
}