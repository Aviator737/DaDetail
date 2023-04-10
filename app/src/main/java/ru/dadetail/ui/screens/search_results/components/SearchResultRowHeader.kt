package ru.dadetail.ui.screens.search_results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dadetail.R
import ru.dadetail.ui.screens.search_results.models.RowType
import ru.dadetail.ui.screens.search_results.models.SearchResultListItem
import ru.dadetail.ui.theme.AppTheme

@Composable
fun SearchResultRowHeader(
    header: SearchResultListItem.SearchResultRowHeaderModel,
    onClick: (SearchResultListItem.SearchResultRowHeaderModel) -> Unit = {}
) {
    val name = when(header.rowType) {
        RowType.REQUEST -> R.string.label_search_result_row_request
        RowType.ORIGINAL -> R.string.label_search_result_row_original
        RowType.NON_ORIGINAL -> R.string.label_search_result_row_non_original
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(header) }
            .padding(8.dp)
            .background(AppTheme.colors.backgroundSecondary)
    ) {
        Text(text = stringResource(name), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = " (${header.amount})", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}