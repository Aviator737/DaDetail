package ru.dadetail.ui.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.R

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Row(modifier = modifier.clickable(onClick = onClick)) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(BorderStroke(1.dp, AppTheme.colors.labelTertiary)),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    tint = AppTheme.colors.red,
                    contentDescription = null,
                    modifier = Modifier.padding(2.dp).fillMaxSize()
                )
            }
        }
        if (text.isNotEmpty()) {
            Text(
                text = text,
                fontSize = 12.sp,
                color = AppTheme.colors.labelSecondary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}