package ru.dadetail.data.api

import retrofit2.Response
import retrofit2.http.*
import ru.dadetail.data.entity.CreateOrderResponse
import ru.dadetail.data.entity.MakeOrderRequest
import ru.dadetail.data.entity.OrdersResponse

interface OrderApi {

    @POST("shop/make_order.html?func=upd")
    @FormUrlEncoded
    suspend fun createOrder(
        @Field("chPos") chPos: String
    ): Response<String>

    @POST("api/v2/client/order/make/")
    suspend fun makeOrder(
        @Body body: MakeOrderRequest,
    ): Response<CreateOrderResponse>

    @GET("api/v2/client/orders/")
    suspend fun getOrders(): Response<OrdersResponse>
}