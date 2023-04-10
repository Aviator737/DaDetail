package ru.dadetail.ui.screens.search_results.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.dadetail.R
import ru.dadetail.ui.common.Button
import ru.dadetail.ui.screens.search_results.models.ReturnType
import ru.dadetail.ui.screens.search_results.models.SearchResultListItem
import ru.dadetail.ui.theme.AppTheme

@Composable
fun SearchResultItemOffer(
    offer: SearchResultListItem.SearchResultOfferModel,
    onAddToCart: (hash: String, sid: String) -> Unit
) {
    val returnTypeR = when(offer.returnType) {
        ReturnType.POSSIBLE -> R.string.label_search_result_offer_return_type_possible
        ReturnType.IMPOSSIBLE -> R.string.label_search_result_offer_return_type_impossible
        ReturnType.LIMITED -> R.string.label_search_result_offer_return_type_limited
        ReturnType.UNKNOWN -> R.string.label_search_result_offer_return_type_unknown
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Text("Возврат: " + stringResource(id = returnTypeR))
            Text(text = "Офицальный диллер: "+ when(offer.isOfficialDealer) {
                true -> "Да"
                false -> "Нет"
                null -> "Неизвестно"
            })
            Text(text = "количество: ${offer.amount}")
            Text(text = "Дата доставки: ${offer.termDelivery}")
            Row {
                Text(text = "Цена: ")
                Text(
                    text = "${offer.price} р.",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Button(
            modifier = Modifier
                .width(96.dp)
                .padding(end = 16.dp, top = 32.dp),
            backgroundColor = AppTheme.colors.backgroundSecondary,
            icon = painterResource(id = R.drawable.ic_cart)
        ) {
            onAddToCart(offer.hash, offer.sid)
        }
    }
}