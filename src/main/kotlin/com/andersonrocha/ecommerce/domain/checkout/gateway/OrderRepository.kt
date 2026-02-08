package com.andersonrocha.ecommerce.domain.checkout.gateway

import com.andersonrocha.ecommerce.domain.checkout.entity.Order

interface OrderRepository {
    fun save(order: Order)
}