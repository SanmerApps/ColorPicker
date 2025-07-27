package dev.sanmer.color.picker.ui.main

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.sanmer.color.picker.Const
import dev.sanmer.color.picker.R
import dev.sanmer.color.picker.ktx.alphaValue
import dev.sanmer.color.picker.ktx.blueValue
import dev.sanmer.color.picker.ktx.greenValue
import dev.sanmer.color.picker.ktx.hexValue
import dev.sanmer.color.picker.ktx.redValue
import dev.sanmer.color.picker.model.ColorValue
import dev.sanmer.color.picker.model.json.ColorJson
import dev.sanmer.color.picker.model.kt.ColorKt
import dev.sanmer.color.picker.model.ui.ColorCompat
import dev.sanmer.color.picker.ui.component.CheckIcon
import dev.sanmer.color.picker.ui.ktx.bottom
import dev.sanmer.color.picker.ui.ktx.surface
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    if (viewModel.color.name.isNotEmpty()) ColorBottomSheet(
        color = viewModel.color,
        onDismiss = { viewModel.color = viewModel.color.copy(name = "") }
    )

    Scaffold(
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ButtonsItem(
                colorValue = viewModel.colorValue,
                setColorValue = viewModel::colorValue::set,
                importJson = viewModel::importFromJson,
                exportJson = viewModel::exportToJson,
                exportKotlin = viewModel::exportToKotlin,
            )

            ColorsItem(
                lightColors = viewModel.lightColors.primary,
                darkColors = viewModel.darkColors.primary,
                onColor = { viewModel.color = it }
            )

            ColorsItem(
                lightColors = viewModel.lightColors.secondary,
                darkColors = viewModel.darkColors.secondary,
                onColor = { viewModel.color = it }
            )

            ColorsItem(
                lightColors = viewModel.lightColors.tertiary,
                darkColors = viewModel.darkColors.tertiary,
                onColor = { viewModel.color = it }
            )

            ColorsItem(
                lightColors = viewModel.lightColors.error,
                darkColors = viewModel.darkColors.error,
                onColor = { viewModel.color = it }
            )

            ColorsItem(
                lightColors = viewModel.lightColors.surface,
                darkColors = viewModel.darkColors.surface,
                onColor = { viewModel.color = it }
            )

            ColorsItem(
                lightColors = viewModel.lightColors.other,
                darkColors = viewModel.darkColors.other,
                onColor = { viewModel.color = it }
            )
        }
    }
}

@Composable
private fun ButtonsItem(
    importJson: (Uri) -> Unit,
    exportJson: (Uri) -> Unit,
    exportKotlin: (Uri) -> Unit,
    colorValue: ColorValue,
    setColorValue: (ColorValue) -> Unit
) = OutlinedCard(
    shape = RoundedCornerShape(15.dp)
) {
    val importJsonLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> if (uri != null) importJson(uri) }
    )

    val exportJsonLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument(ColorJson.MIME_TYPE),
        onResult = { uri -> if (uri != null) exportJson(uri) }
    )

    val exportKotlinLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument(ColorKt.MIME_TYPE),
        onResult = { uri -> if (uri != null) exportKotlin(uri) }
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ColorValueButton(
            colorValue = colorValue,
            setColorValue = setColorValue
        )

        OutlinedButton(
            onClick = { importJsonLauncher.launch(ColorJson.MIME_TYPE) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.json),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))

            Text(text = stringResource(id = R.string.home_import))
        }

        FilledTonalButton(
            onClick = { exportJsonLauncher.launch(ColorJson.FILE_NAME) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.json),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))

            Text(text = stringResource(id = R.string.home_export))
        }

        FilledTonalButton(
            onClick = { exportKotlinLauncher.launch(ColorKt.FILE_NAME) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.brand_kotlin),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))

            Text(text = stringResource(id = R.string.home_export))
        }
    }
}

@Composable
private fun ColorValueButton(
    colorValue: ColorValue,
    setColorValue: (ColorValue) -> Unit
) = SingleChoiceSegmentedButtonRow {
    ColorValue.entries.forEachIndexed { index, value ->
        SegmentedButton(
            selected = colorValue == value,
            onClick = { setColorValue(value) },
            shape = SegmentedButtonDefaults.itemShape(
                index = index,
                count = ColorValue.entries.size
            ),
            icon = { SegmentedButtonDefaults.CheckIcon(colorValue == value) }
        ) {
            Text(text = value.name)
        }
    }
}

@Composable
private fun ColorsItem(
    lightColors: List<ColorCompat>,
    darkColors: List<ColorCompat>,
    onColor: (ColorCompat) -> Unit
) = Surface(
    shape = RoundedCornerShape(15.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            lightColors.forEach {
                ColorItem(it) {
                    onColor(it)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            darkColors.forEach {
                ColorItem(it) {
                    onColor(it)
                }
            }
        }
    }
}

@Composable
private fun ColorItem(
    color: ColorCompat,
    onClick: () -> Unit
) = Box(
    modifier = Modifier
        .clickable(
            enabled = true,
            onClick = onClick
        )
        .background(color = color.containerColor)
        .fillMaxWidth()
        .height(60.dp)
        .padding(all = 10.dp),
    contentAlignment = Alignment.TopStart
) {
    Text(
        text = color.name,
        color = color.contentColor,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun ColorBottomSheet(
    color: ColorCompat,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val hexValue by remember { derivedStateOf { color.containerColor.hexValue } }
    val rgbValue by remember {
        derivedStateOf {
            with(color.containerColor) { "(${redValue}, ${greenValue}, ${blueValue}, ${alphaValue})" }
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        shape = MaterialTheme.shapes.large.bottom(0.dp)
    ) {
        Text(
            text = color.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )

        ValueItem(
            icon = R.drawable.color_filter,
            value = rgbValue,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        ValueItem(
            icon = R.drawable.hash,
            value = hexValue,
            modifier = Modifier.padding(all = 20.dp)
        )
    }
}

@Composable
private fun ValueItem(
    @DrawableRes icon: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .surface(
                shape = MaterialTheme.shapes.medium,
                backgroundColor = MaterialTheme.colorScheme.surface,
                border = CardDefaults.outlinedCardBorder()
            )
            .clickable(
                enabled = true,
                onClick = {
                    scope.launch {
                        clipboard.setClipEntry(
                            ClipData.newPlainText("", value).toClipEntry()
                        )
                    }
                }
            )
            .padding(all = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior
) = TopAppBar(
    title = { Text(text = stringResource(id = R.string.app_name)) },
    actions = {
        val context = LocalContext.current
        IconButton(
            onClick = {
                context.startActivity(
                    Intent.parseUri(Const.GITHUB_URL, Intent.URI_INTENT_SCHEME)
                )
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.brand_github),
                contentDescription = null
            )
        }
    },
    scrollBehavior = scrollBehavior
)