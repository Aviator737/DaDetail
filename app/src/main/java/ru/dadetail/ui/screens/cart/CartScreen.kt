package ru.dadetail.ui.screens.cart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import ru.dadetail.R
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.SIDE_EFFECTS_KEY
import ru.dadetail.ui.common.Button
import ru.dadetail.ui.common.EmptyStub
import ru.dadetail.ui.common.FullScreenLoading
import ru.dadetail.ui.common.ListDivider
import ru.dadetail.ui.theme.AppTheme

@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is CartContract.Effect.OrderCreatedToast ->
                    Toast.makeText(context, "Заказ успешно создан", Toast.LENGTH_SHORT).show()
            }
        }.collect()
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(AppTheme.colors.backgroundSecondary)
    ) {
        when(val items = state.value.items) {
            is Lce.Content -> {
                if (items.data.isEmpty()) {
                    EmptyStub(
                        icon = painterResource(id = R.drawable.ic_trash),
                        primaryText = stringResource(id = R.string.label_empty_cart_primary)
                    )
                } else {
                    Scaffold(
                        topBar = {},
                        bottomBar = {
                            Button(
                                modifier = Modifier.height(74.dp),
                                isEnabled = state.value.submitEnabled,
                                isLoading = state.value.orderInProgress,
                                text = stringResource(id = R.string.label_cart_create_order),
                                fontSize = 22.sp,
                            ) {
                                viewModel.setEvent(CartContract.Event.OnSubmit)
                            }
                        }
                    ) { paddingValues ->
                        LazyColumn(modifier = Modifier.padding(paddingValues)) {
                            itemsIndexed(
                                items = items.data
                            ) { index, item ->
                                CartItem(
                                    item = item,
                                    onDelete = { viewModel.setEvent(CartContract.Event.OnRemoveFromCart(it)) },
                                    onSelect = { viewModel.setEvent(CartContract.Event.OnSelectItem(it)) }
                                )
                                ListDivider(index, items.data.size)
                            }
                        }
                    }
                }
            }
            is Lce.Error -> {
                Text(text = "Не удалось загрузить данные")
            }
            is Lce.Loading -> FullScreenLoading()
        }
    }
}