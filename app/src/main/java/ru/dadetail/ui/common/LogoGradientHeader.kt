package ru.dadetail.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LogoGradientHeader(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CompanyGradient()
        Logo(modifier = Modifier.padding(top = 26.dp, start = 30.dp))
    }
}

@Preview
@Composable
fun LogoGradientHeaderPreview() {
    LogoGradientHeader()
}