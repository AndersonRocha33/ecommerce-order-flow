package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.entity.OrderItem
import java.math.BigDecimal




class CreateOrderUseCase {

    fun execute(items: List<InputItem>): Order{
        val orderItems = items.map{item ->
            OrderItem(
                productId = item.productId,
                price = item.price,
                quantity = item.quantity
            )

        }
        return Order(items = orderItems)
    }

    data class InputItem(
        val productId: String,
        val price: BigDecimal,
        val quantity: Int
    )
}