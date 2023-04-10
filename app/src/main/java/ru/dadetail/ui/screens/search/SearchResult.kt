package ru.dadetail.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchResult(
    item: SearchResultModel,
    onClick: (item: SearchResultModel) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                onClick(item)
            }
    ) {
        if (item.prdName.isNotEmpty() || item.name.isNotEmpty()) {
            Row {
                Text(text = item.prdName)
                Text(text = item.name, modifier = Modifier.padding(start = 8.dp))
            }
        }
        if (item.comment.isNotEmpty()) {
            Text(text = item.comment, modifier = Modifier.padding(top = 4.dp))
        }
    }
}