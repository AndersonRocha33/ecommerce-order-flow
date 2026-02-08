package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository

class CheckoutOrderUseCase(
    private val orderRepository: OrderRepository,
    private val paymentGateway: PaymentGateway,
    private val orderQueue: OrderQueue
) {

    fun execute(order: Order) {
        val paymentApproved = paymentGateway.pay(order.total())

        if (!paymentApproved) {
            throw IllegalArgumentException("Pagamento recusado")
        }

        orderRepository.save(order)
        orderQueue.publish(order)
    }
}
