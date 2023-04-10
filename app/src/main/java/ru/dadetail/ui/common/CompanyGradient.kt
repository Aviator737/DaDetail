package ru.dadetail.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CompanyGradient(
    modifier: Modifier = Modifier
) {
    val colorStops = arrayOf(
        0.0f to Color(17, 17, 17),
        1f to Color(195, 16, 28)
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Brush.horizontalGradient(colorStops = colorStops))
    )
}

@Preview
@Composable
fun CompanyGradientPreview() {
    CompanyGradient()
}