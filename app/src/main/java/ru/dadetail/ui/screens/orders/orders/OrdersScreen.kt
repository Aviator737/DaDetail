package ru.dadetail.ui.screens.orders.orders

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.dadetail.R
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.common.EmptyStub
import ru.dadetail.ui.common.FullScreenLoading
import ru.dadetail.ui.common.ListDivider

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    when(val items = state.value.items) {
        is Lce.Content -> {
            if (items.data.isEmpty()) {
                EmptyStub(
                    icon = painterResource(id = R.drawable.ic_orders),
                    primaryText = stringResource(id = R.string.label_empty_orders_primary),
                    secondaryText = stringResource(id = R.string.label_empty_orders_secondary)
                )
            } else {
                LazyColumn(Modifier.padding(top = 30.dp)) {
                    itemsIndexed(items.data) { index, item ->
                        OrderItem(item)
                        ListDivider(index, items.data.size)
                    }
                }
            }
        }
        is Lce.Error -> {
            Text(text = stringResource(id = R.string.label_error_failed_fetch_data))
        }
        is Lce.Loading -> FullScreenLoading()
    }
}