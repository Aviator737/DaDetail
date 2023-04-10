package ru.dadetail.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.ui.theme.defaultButtonHeight
import ru.dadetail.ui.theme.defaultElementsShape

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    icon: Painter? = null,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    backgroundGradient: Brush? = null,
    backgroundColor: Color = AppTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    val modifierWithBg = if (isEnabled) {
        if (backgroundGradient == null) {
            modifier.background(backgroundColor, defaultElementsShape)
        } else {
            modifier.background(backgroundGradient, defaultElementsShape)
        }
    } else {
        modifier.background(AppTheme.colors.gray3, defaultElementsShape)
    }

    Row(
        modifier = modifierWithBg
            .fillMaxWidth()
            .defaultMinSize(minHeight = defaultButtonHeight)
            .clickable(
                enabled = !isLoading && isEnabled,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon == null) {
            Text(
                text = text,
                fontSize = fontSize,
                color = AppTheme.colors.forceWhitePrimary,
                modifier = Modifier.padding(start = 20.dp)
            )
        } else {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
            )
        }
        Column(
            modifier = Modifier.width(22.dp)
        ) {
            if (isLoading)
                CircularProgressIndicator(
                    color =  AppTheme.colors.forceWhitePrimary,
                    strokeWidth = 1.dp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(13.dp)
                )
        }
    }
}