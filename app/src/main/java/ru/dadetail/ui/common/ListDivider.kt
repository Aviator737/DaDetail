package ru.dadetail.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dadetail.ui.theme.AppTheme

@Composable
fun ListDivider(index: Int, size: Int) {
    if (index < size-1) {
        Box(Modifier.height(1.dp).fillMaxWidth().background(AppTheme.colors.transparent)) {
            Box(Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(AppTheme.colors.forceDarkQuaternary)
            )
        }
    }
}