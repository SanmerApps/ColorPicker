package dev.sanmer.color.picker.ui.main

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.sanmer.color.picker.Const
import dev.sanmer.color.picker.R
import dev.sanmer.color.picker.model.ColorJson
import dev.sanmer.color.picker.model.ColorKt
import dev.sanmer.color.picker.model.ColorSchemeCompat
import dev.sanmer.color.picker.viewmodel.HomeViewModel

@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

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
                importJson = viewModel::importFromJson,
                exportJson = viewModel::exportToJson,
                exportKotlin = viewModel::exportToKotlin
            )

            ColorsItem(
                lightColors = viewModel.lightColors.primary,
                darkColors = viewModel.darkColors.primary
            )

            ColorsItem(
                lightColors = viewModel.lightColors.secondary,
                darkColors = viewModel.darkColors.secondary
            )

            ColorsItem(
                lightColors = viewModel.lightColors.tertiary,
                darkColors = viewModel.darkColors.tertiary
            )

            ColorsItem(
                lightColors = viewModel.lightColors.error,
                darkColors = viewModel.darkColors.error
            )

            ColorsItem(
                lightColors = viewModel.lightColors.surface,
                darkColors = viewModel.darkColors.surface
            )

            ColorsItem(
                lightColors = viewModel.lightColors.other,
                darkColors = viewModel.darkColors.other
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonsItem(
    importJson: (Uri) -> Unit,
    exportJson: (Uri) -> Unit,
    exportKotlin: (Uri) -> Unit
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
private fun ColorsItem(
    lightColors: List<ColorSchemeCompat.Color>,
    darkColors: List<ColorSchemeCompat.Color>
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
                ColorItem(it)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            darkColors.forEach {
                ColorItem(it)
            }
        }
    }
}

@Composable
private fun ColorItem(
    color: ColorSchemeCompat.Color
) = Box(
    modifier = Modifier
        .background(color = color.containerColor)
        .fillMaxWidth()
        .height(60.dp),
    contentAlignment = Alignment.TopStart
) {
    Text(
        text = color.name,
        color = color.contentColor,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(all = 10.dp),
    )
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