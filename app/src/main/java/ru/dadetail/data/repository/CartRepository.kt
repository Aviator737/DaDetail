package ru.dadetail.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dadetail.data.api.CartApi
import ru.dadetail.data.entity.*

class CartRepository(private val cartApi: CartApi) {
    suspend fun getCart() = withContext(Dispatchers.IO) {
        cartApi.getCart()
    }

    suspend fun addToCart(hash: String, sid: String) = withContext(Dispatchers.IO) {
        cartApi.addToCart(
            func = "add",
            sid = sid,
            amount = "1",
            hash = hash
        )
    }

    suspend fun changeAmount(id: Long, amount: Int) {
        cartApi.changeAmount(
            CartPositionChangeAmountRequest(positionID = id, amount = amount)
        )
    }

    suspend fun removeFromCart(id: Long) = withContext(Dispatchers.IO) {
        cartApi.removeFromCart(RemovePositionFromCartRequest(positionID = id))
    }
}