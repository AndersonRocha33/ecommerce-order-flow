package com.andersonrocha.ecommerce

import com.andersonrocha.ecommerce.application.checkout.CheckoutOrderApplicationService
import com.andersonrocha.ecommerce.domain.checkout.usecase.CreateOrderUseCase
import com.andersonrocha.ecommerce.infra.messaging.FakeSqsOrderQueue
import com.andersonrocha.ecommerce.infra.payment.FakeHttpPaymentGateway
import com.andersonrocha.ecommerce.infra.persistence.InMemoryOrderRepository
import java.math.BigDecimal

fun main() {
    val repository = InMemoryOrderRepository()
    val paymentGateway = FakeHttpPaymentGateway()
    val orderQueue = FakeSqsOrderQueue()

    val service = CheckoutOrderApplicationService(
        repository,
        paymentGateway,
        orderQueue
    )

    // Criar pedidos de teste
    val items = listOf(
        CreateOrderUseCase.InputItem("produto-1", BigDecimal("20.00"), 2),
        CreateOrderUseCase.InputItem("produto-2", BigDecimal("15.50"), 1)
    )

    service.checkout(items)
}