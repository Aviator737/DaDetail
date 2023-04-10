package ru.dadetail.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.dadetail.R
import ru.dadetail.ui.common.CheckBox
import ru.dadetail.ui.theme.AppTheme

@Composable
fun CartItem(
    item: CartItemModel,
    onDelete: (Long) -> Unit = {},
    onSelect: (CartItemModel) -> Unit = {}
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.backgroundTertiary)
        .padding(16.dp)
    ) {
        CheckBox(
            modifier = Modifier.padding(top = 8.dp, end = 8.dp),
            checked = item.isSelected
        ) {
            onSelect(item)
        }
        if (item.image.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.no_photo),
                contentDescription = null
            )
        } else {
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = item.image,
                contentDescription = null
            )
        }
        Column(Modifier.padding(start = 8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(item.name, color = AppTheme.colors.labelPrimary)
                    Text(item.article, color = AppTheme.colors.labelSecondary)
                    Text(item.termDisplay, modifier = Modifier.padding(top = 8.dp))
                }
                Image(
                    modifier = Modifier.clickable { onDelete(item.id) }.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Удалить позицию"
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Column(horizontalAlignment = Alignment.End) {
                    Text("${item.price} "
                            + stringResource(id = R.string.label_currency_rub)
                            + stringResource(id = R.string.label_divider_slash)
                            + stringResource(id = R.string.label_part_short),
                        color = AppTheme.colors.labelSecondary)
                    Text("${item.sum} " + stringResource(id = R.string.label_currency_rub), color = AppTheme.colors.labelPrimary)
                }
            }
        }
    }
}

@Preview
@Composable
fun CartItemPreview() {
    CartItem(
        item = CartItemModel(
            0,
        "Тест запчасть",
            "тест артикул 123131",
            "190",
            "26 марта",
            "1000",
            "урл картинки",
            true
        )
    )
}