package com.andersonrocha.ecommerce.infra.repository

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository

class FakeOrderRepository : OrderRepository {

    override fun save(order: Order) {
        println("💾 Pedido salvo no banco (memória)")
    }
}