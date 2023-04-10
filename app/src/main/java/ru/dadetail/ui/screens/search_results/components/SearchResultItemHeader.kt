package ru.dadetail.ui.screens.search_results.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.dadetail.R
import ru.dadetail.ui.screens.search_results.models.SearchResultListItem
import ru.dadetail.ui.theme.AppTheme

@Composable
fun SearchResultItemHeader(
    item: SearchResultListItem.SearchResultRowItemModel,
    onAddToFavourites: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        if (item.image == null) {
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
        Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp)) {
            Row {
                Text(text = item.brand, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    text = item.article,
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = AppTheme.colors.blue
                )
            }
            Text(
                text = item.description,
                color = AppTheme.colors.labelSecondary
            )
        }
    }
}