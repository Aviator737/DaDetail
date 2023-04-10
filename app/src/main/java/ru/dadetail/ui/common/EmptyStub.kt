package ru.dadetail.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dadetail.ui.theme.AppTheme

@Composable
fun EmptyStub(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    primaryText: String = "",
    secondaryText: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon?.let {
            Icon(
                painter = icon,
                contentDescription = primaryText,
                tint = AppTheme.colors.red,
                modifier = Modifier.size(70.dp)
            )
        }
        Text(
            text = primaryText,
            fontSize = 22.sp,
            fontWeight = FontWeight.W600,
            color = AppTheme.colors.labelPrimary,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = secondaryText,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.labelSecondary,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}