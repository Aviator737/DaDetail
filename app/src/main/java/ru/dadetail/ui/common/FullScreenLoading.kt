package ru.dadetail.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import ru.dadetail.ui.theme.AppTheme

@Composable
fun FullScreenLoading(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    backgroundColor: Color = AppTheme.colors.transparent,
    strokeCap: StrokeCap = StrokeCap.Square,
) {
    Box(modifier = modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = strokeWidth,
            backgroundColor = backgroundColor,
            strokeCap = strokeCap
        )
    }
}