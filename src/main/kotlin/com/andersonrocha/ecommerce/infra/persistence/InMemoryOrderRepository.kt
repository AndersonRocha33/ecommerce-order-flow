package com.andersonrocha.ecommerce.infra.persistence

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository

class InMemoryOrderRepository : OrderRepository {

    private val database = mutableListOf<Order>()

    override fun save(order: Order) {
        database.add(order)
        println("💾 Pedido salvo no banco (memória)")
    }
}