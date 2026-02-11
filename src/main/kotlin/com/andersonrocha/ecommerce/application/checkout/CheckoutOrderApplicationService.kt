package com.andersonrocha.ecommerce.application.checkout

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository
import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import com.andersonrocha.ecommerce.domain.checkout.usecase.CheckoutOrderUseCase
import com.andersonrocha.ecommerce.domain.checkout.usecase.CreateOrderUseCase

class CheckoutOrderApplicationService(
    private val orderRepository: OrderRepository,
    private val paymentGateway: PaymentGateway,
    private val orderQueue: OrderQueue
) {

    private val createOrderUseCase = CreateOrderUseCase()
    private val checkoutOrderUseCase = CheckoutOrderUseCase(
        paymentGateway = paymentGateway,
        orderRepository = orderRepository,
        orderQueue = orderQueue
    )

    fun checkout(items: List<CreateOrderUseCase.InputItem>) {
        // 1️⃣ Criar o Order a partir dos InputItem
        val order: Order = createOrderUseCase.execute(items)

        // 2️⃣ Executar o checkout (pagamento, salvar, publicar)
        checkoutOrderUseCase.execute(order)
    }
}