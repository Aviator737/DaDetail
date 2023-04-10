package ru.dadetail.data.api

import retrofit2.Response
import retrofit2.http.*
import ru.dadetail.data.entity.*

interface CartApi {
    @GET("api/v2/client/basket-items/")
    suspend fun getCart(): Response<CartResponse>

    @HTTP(
        method = "DELETE",
        path = "api/v2/client/basket-positions/",
        hasBody = true
    )
    suspend fun removeFromCart(
        @Body body: RemovePositionFromCartRequest
    ): Response<CartUpdatePositionResponse>

    @GET("_ajax/basket/")
    suspend fun addToCart(
        @Query("func") func: String,
        @Query("sid") sid: String,
        @Query("amount") amount: String,
        @Query("hash") hash: String,
    ): Response<AddToCartResponse>

    @PUT("api/v2/client/basket-positions/")
    suspend fun changeAmount(
        @Body body: CartPositionChangeAmountRequest
    ): Response<CartUpdatePositionResponse>
}