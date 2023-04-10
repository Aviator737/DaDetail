package ru.dadetail.ui.screens.orders.positions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dadetail.ui.theme.AppTheme

@Composable
fun PositionItem(item: PositionItemModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.backgroundTertiary)
        .padding(16.dp)
    ) {
        Row {
            Text("ID заказа: ")
            Text(item.positionID.toString())
        }
        Row {
            Text("Наименование: ")
            Text(item.brand)
        }
        Row {
            Text("Артикул: ")
            Text(item.article)
        }
        Row {
            Text("Количество: ")
            Text(item.amount)
        }
        Row {
            Text("Дата доставки: ")
            Text(item.arrivalDate)
        }
        Row {
            Text("Статус заказа: ")
            Text(item.statusName)
        }
        Row {
            Text("Цена: ")
            Text(item.payedSum)
        }
        Row {
            Text("Сумма: ")
            Text(item.sum)
        }
        Row {
            Text("Долг: -")
            Text(item.debtSum)
        }
    }
}