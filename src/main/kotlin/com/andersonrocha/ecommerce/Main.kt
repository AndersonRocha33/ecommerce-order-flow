package com.andersonrocha.ecommerce

import com.andersonrocha.ecommerce.application.checkout.CheckoutOrderApplicationService
import com.andersonrocha.ecommerce.domain.checkout.usecase.CreateOrderUseCase
import com.andersonrocha.ecommerce.infra.messaging.FakeSqsOrderQueue
import com.andersonrocha.ecommerce.infra.payment.FakeHttpPaymentGateway
import com.andersonrocha.ecommerce.infra.persistence.InMemoryOrderRepository
import com.andersonrocha.ecommerce.domain.checkout.exception.PaymentRefusedException
import java.math.BigDecimal
import com.andersonrocha.ecommerce.infra.worker.LogisticsWorker

fun main() {

    // 🔌 Infra (mundo real — fake por enquanto)
    val orderRepository = InMemoryOrderRepository()
    val paymentGateway = FakeHttpPaymentGateway()
    val orderQueue = FakeSqsOrderQueue()

    // 🧠 Application
    val checkoutService = CheckoutOrderApplicationService(
        orderRepository = orderRepository,
        paymentGateway = paymentGateway,
        orderQueue = orderQueue
    )

    // 🛒 Simulação de entrada (Controller)
    val items = listOf(
        CreateOrderUseCase.InputItem(
            productId = "SKU-123",
            quantity = 2,
            price = BigDecimal("50.00")
        ),
        CreateOrderUseCase.InputItem(
            productId = "SKU-456",
            quantity = 1,
            price = BigDecimal("30.00")
        )
    )

    try {
        checkoutService.checkout(items)
        val worker = LogisticsWorker(orderQueue)
        worker.start()
        println("✅ Pedido processado com sucesso!")
    } catch (e: PaymentRefusedException) {
        println("❌ Falha no pagamento: ${e.message}")
    }
}
