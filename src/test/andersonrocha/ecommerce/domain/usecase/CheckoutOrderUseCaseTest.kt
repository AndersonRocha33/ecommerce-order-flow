package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.entity.OrderItem
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository
import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import java.math.BigDecimal

class CheckoutOrderUseCase(
    private val paymentGateway: PaymentGateway,
    private val orderRepository: OrderRepository,
    private val orderQueue: OrderQueue
) {

    fun execute(items: List<InputItem>) {

        val orderItems = items.map {
            OrderItem(
                productId = it.productId,
                price = it.price,
                quantity = it.quantity
            )
        }

        val order = Order(items = orderItems)

        val paymentSuccess = paymentGateway.pay(order.total())

        if (!paymentSuccess) {
            throw IllegalStateException("Payment failed")
        }

        orderRepository.save(order)
        orderQueue.publish(order)
    }

    data class InputItem(
        val productId: String,
        val price: BigDecimal,
        val quantity: Int
    )
}
