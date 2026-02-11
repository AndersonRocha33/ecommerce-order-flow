package com.andersonrocha.ecommerce.infra.messaging

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue

class FakeSqsOrderQueue : OrderQueue {

    private val queue = mutableListOf<Order>()
    override fun publish(order: Order) {
        println("📦 Mensagem enviada para SQS - Pedido ${order.id}")
        queue.add(order)
    }

    override fun consume(): Order? {
        return if (queue.isNotEmpty()) {
            val order = queue.removeAt(0)
            println("📥 Mensagem consumida da fila - Pedido ${order.id}")
            order
        } else {
            null
        }
    }
}
