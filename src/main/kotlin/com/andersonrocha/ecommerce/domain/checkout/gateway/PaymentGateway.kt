package com.andersonrocha.ecommerce.domain.checkout.gateway

import java.math.BigDecimal

interface PaymentGateway {
    fun pay(amount: BigDecimal): Boolean
}