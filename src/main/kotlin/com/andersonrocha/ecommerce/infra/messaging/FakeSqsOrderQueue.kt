package com.andersonrocha.ecommerce.infra.messaging

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue

class FakeSqsOrderQueue : OrderQueue {

    override fun publish(order: Order) {
        println("📦 Mensagem enviada para SQS - Pedido ${order.id}")
    }
}