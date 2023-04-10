package ru.dadetail.ui.screens.orders.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dadetail.ui.theme.AppTheme

@Composable
fun OrderItem(item: OrderItemModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.backgroundTertiary)
        .padding(16.dp)
    ) {
        Text(item.orderID)
        Text(item.orderNumber)
        Text(item.dateTime)
        Text(item.deliveryTypeName)
    }
}