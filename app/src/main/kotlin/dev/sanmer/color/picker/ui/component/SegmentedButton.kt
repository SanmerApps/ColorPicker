package dev.sanmer.color.picker.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dev.sanmer.color.picker.R

@Composable
fun SegmentedButtonDefaults.CheckIcon(
    active: Boolean
) = Icon(
    active = active,
    activeContent = {
        Icon(
            painter = painterResource(R.drawable.check),
            contentDescription = null,
            modifier = Modifier.size(IconSize)
        )
    }
)