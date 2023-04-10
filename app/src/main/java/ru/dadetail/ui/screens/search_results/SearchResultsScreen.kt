package ru.dadetail.ui.screens.search_results

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.SIDE_EFFECTS_KEY
import ru.dadetail.ui.screens.search_results.components.SearchResultItemHeader
import ru.dadetail.ui.screens.search_results.components.SearchResultItemOffer
import ru.dadetail.ui.screens.search_results.components.SearchResultRowHeader
import ru.dadetail.ui.screens.search_results.models.SearchResultListItem
import ru.dadetail.ui.theme.AppTheme

@Composable
fun SearchResultsScreen(
    viewModel: SearchResultsViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is SearchResultsContract.Effect.AddedToCartToast ->
                    Toast.makeText(context, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }.collect()
    }

    val listContent = state.value.listContent

    when(listContent) {
        is Lce.Content -> {
            LazyColumn(modifier = Modifier.background(AppTheme.colors.backgroundPrimary)) {
                itemsIndexed(listContent.data) { index, item ->
                    when(item) {
                        is SearchResultListItem.SearchResultRowHeaderModel ->
                            SearchResultRowHeader(item) { id ->
                                viewModel.setEvent(SearchResultsContract.Event.OnSwitchRowExpanded(id))
                            }
                        is SearchResultListItem.SearchResultRowItemModel -> SearchResultItemHeader(item)
                        is SearchResultListItem.SearchResultOfferModel -> {
                            Divider()
                            SearchResultItemOffer(item) { hash, sid ->
                                viewModel.setEvent(SearchResultsContract.Event.OnAddToCart(hash, sid))
                            }
                        }
                    }
                }
            }
        }
        is Lce.Error -> {}
        is Lce.Loading -> {}
    }
}