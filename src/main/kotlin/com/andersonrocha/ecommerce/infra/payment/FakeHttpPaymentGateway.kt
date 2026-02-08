package com.andersonrocha.ecommerce.infra.payment

import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import java.math.BigDecimal

class FakeHttpPaymentGateway : PaymentGateway {

    override fun pay(amount: BigDecimal): Boolean {
        println("💳 Processando pagamento via HTTP: R$ $amount")
        return true
    }
}