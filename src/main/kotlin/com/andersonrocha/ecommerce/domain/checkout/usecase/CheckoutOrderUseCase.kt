package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository
import com.andersonrocha.ecommerce.domain.checkout.exception.PaymentRefusedException

class CheckoutOrderUseCase(
    private val paymentGateway: PaymentGateway,
    private val orderRepository: OrderRepository,
    private val orderQueue: OrderQueue
) {

    fun execute(order: Order) {
        val paymentApproved = paymentGateway.pay(order.total())

        if (!paymentApproved) {
            throw PaymentRefusedException()
        }

        orderRepository.save(order)
        orderQueue.publish(order)
    }
}
