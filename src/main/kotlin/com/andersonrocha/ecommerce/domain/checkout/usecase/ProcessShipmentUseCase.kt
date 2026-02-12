package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue

class ProcessShipmentUseCase(
    private val orderQueue: OrderQueue
) {

    fun execute() {
        val order = orderQueue.consume()

        if (order != null) {
            order.markAsShipped()
            println("🚚 Pedido ${order.id} enviado")
        }
    }
}