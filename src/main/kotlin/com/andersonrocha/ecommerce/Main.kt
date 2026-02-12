package com.andersonrocha.ecommerce

import com.andersonrocha.ecommerce.application.checkout.CheckoutOrderApplicationService
import com.andersonrocha.ecommerce.domain.checkout.usecase.CreateOrderUseCase
import com.andersonrocha.ecommerce.infra.messaging.FakeSqsOrderQueue
import com.andersonrocha.ecommerce.infra.repository.FakeOrderRepository
import com.andersonrocha.ecommerce.infra.payment.FakeHttpPaymentGateway
import com.andersonrocha.ecommerce.domain.checkout.usecase.ProcessShipmentUseCase
import java.math.BigDecimal
import com.andersonrocha.ecommerce.infra.worker.LogisticsWorker

fun main() {

    val orderRepository = FakeOrderRepository()
    val paymentGateway = FakeHttpPaymentGateway()
    val orderQueue = FakeSqsOrderQueue()

    val checkoutService = CheckoutOrderApplicationService(
        orderRepository,
        paymentGateway,
        orderQueue
    )

    // 🔥 NOVO
    val processShipmentUseCase = ProcessShipmentUseCase(orderQueue)
    val logisticsWorker = LogisticsWorker(processShipmentUseCase)

    checkoutService.checkout(
        listOf(
            CreateOrderUseCase.InputItem(
                productId = "1",
                quantity = 2,
                price = BigDecimal("50.00")
            )
        )
    )

    logisticsWorker.start()
}