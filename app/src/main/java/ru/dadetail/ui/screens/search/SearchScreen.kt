package ru.dadetail.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.dadetail.R
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.common.Input
import ru.dadetail.ui.common.ListDivider
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.ui.theme.bottomSheetShape

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val searchResults = state.value.searchResults

    Box {
        Column(
            modifier = Modifier
                .padding(top = 115.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(AppTheme.colors.backgroundSecondary, bottomSheetShape),
            verticalArrangement = Arrangement.SpaceBetween
        ) {}
        Column(modifier = Modifier.padding(top = 26.dp, start = 26.dp, end = 26.dp)) {
            Input(
                hint = stringResource(id = R.string.label_search),
                text = state.value.search,
                isLoading = searchResults is Lce.Loading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                icon = painterResource(id = R.drawable.ic_search)
            ) { viewModel.setEvent(SearchContract.Event.OnSearchInput(it)) }
            when(searchResults) {
                is Lce.Content -> {
                    LazyColumn(modifier = Modifier.background(AppTheme.colors.backgroundPrimary)) {
                        itemsIndexed(searchResults.data) { index, item ->
                            SearchResult(item) {
                                viewModel.setEvent(SearchContract.Event.OnSearchResultClicked(it))
                            }
                            ListDivider(index, searchResults.data.size)
                        }
                    }
                }
                is Lce.Error -> {}
                is Lce.Loading -> {}
            }
        }
    }
}