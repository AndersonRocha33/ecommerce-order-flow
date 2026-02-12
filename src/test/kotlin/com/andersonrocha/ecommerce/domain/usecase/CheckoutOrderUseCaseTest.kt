package com.andersonrocha.ecommerce.domain.checkout.usecase

import com.andersonrocha.ecommerce.domain.checkout.entity.Order
import com.andersonrocha.ecommerce.domain.checkout.entity.OrderItem
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue
import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderRepository
import com.andersonrocha.ecommerce.domain.checkout.gateway.PaymentGateway
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import com.andersonrocha.ecommerce.domain.checkout.exception.PaymentRefusedException

class CheckoutOrderUseCaseTest {

    @Test
    fun `should save order and publish event when payment is approved`() {

        // Fakes
        val fakeRepository = FakeOrderRepository()
        val fakePaymentGateway = FakePaymentGateway(true)
        val fakeQueue = FakeOrderQueue()

        val useCase = CheckoutOrderUseCase(
            orderRepository = fakeRepository,
            paymentGateway = fakePaymentGateway,
            orderQueue = fakeQueue
        )

        val order = Order(
            id = UUID.randomUUID(),
            items = listOf(
                OrderItem("Produto", BigDecimal("20.00"), 1)
            )
        )

        useCase.execute(order)

        assertTrue(fakeRepository.wasSaved)
        assertTrue(fakeQueue.wasPublished)
    }

    @Test
    fun `should not save or publish when payment is refused`() {

        val fakeRepository = FakeOrderRepository()
        val fakePaymentGateway = FakePaymentGateway(false) // pagamento recusado
        val fakeQueue = FakeOrderQueue()

        val useCase = CheckoutOrderUseCase(
            orderRepository = fakeRepository,
            paymentGateway = fakePaymentGateway,
            orderQueue = fakeQueue
        )

        val order = Order(
            id = UUID.randomUUID(),
            items = listOf(
                OrderItem("Produto", BigDecimal("20.00"), 1)
            )
        )

        org.junit.jupiter.api.Assertions.assertThrows(
            PaymentRefusedException::class.java
        ) {
            useCase.execute(order)
        }

        assertTrue(!fakeRepository.wasSaved)
        assertTrue(!fakeQueue.wasPublished)
    }


}

/* ============================
   Fakes para teste
   ============================ */

class FakeOrderRepository : OrderRepository {

    var wasSaved = false

    override fun save(order: Order) {
        wasSaved = true
    }
}

class FakePaymentGateway(
    private val shouldApprove: Boolean
) : PaymentGateway {

    override fun pay(amount: BigDecimal): Boolean {
        return shouldApprove
    }
}

class FakeOrderQueue : OrderQueue {

    var wasPublished = false

    override fun publish(order: Order) {
        wasPublished = true
    }

    override fun consume(): Order? {
        return null
    }
}