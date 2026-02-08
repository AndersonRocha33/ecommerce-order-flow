package com.andersonrocha.ecommerce.domain.checkout.gateway

import com.andersonrocha.ecommerce.domain.checkout.entity.Order

interface OrderQueue{
    fun publish(order: Order)
}