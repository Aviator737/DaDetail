package ru.dadetail.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dadetail.data.api.OrderApi
import ru.dadetail.data.entity.CreateOrderData
import ru.dadetail.data.entity.CreateOrderResponse
import ru.dadetail.data.entity.MakeOrderRequest
import ru.dadetail.util.extentions.parseObject

class OrderRepository(private val orderApi: OrderApi) {

    suspend fun getOrders() = withContext(Dispatchers.IO) {
        orderApi.getOrders()
    }

    suspend fun createOrder(positionIds: List<Long>) = withContext(Dispatchers.IO) {
        val createOrderResult = orderApi.createOrder(positionIds.joinToString().replace(" ", ""))
        if (createOrderResult.isSuccessful) {
            val obj = createOrderResult.body()?.parseObject<CreateOrderResponse>("MakeOrder(", "})")
            if (obj != null) {
                return@withContext makeOrder(obj.orderData)
            }
        }
        return@withContext null
    }

    suspend fun makeOrder(orderData: CreateOrderData) = withContext(Dispatchers.IO) {
        orderApi.makeOrder(
            body = MakeOrderRequest(
                address = null,
                addressId = orderData.addressId,
                carId = orderData.carId,
                cityId = orderData.cityId,
                comment = "test android app order",
                contact = orderData.contact ?: "",
                deliveryId = orderData.deliveryId,
                deliveryTariffId = 0,
                districtId = orderData.districtId,
                email = orderData.email,
                isPickup = false,
                needValidation = false,
                orderId = orderData.orderId,
                paymentFromBalanceIsActive = false,
                paymentKindsId = orderData.paymentKindsId,
                phone = orderData.phone ?: "",
                pointId = orderData.pointId,
                positions = orderData.positions.map { it.id },
            )
        )
    }
}