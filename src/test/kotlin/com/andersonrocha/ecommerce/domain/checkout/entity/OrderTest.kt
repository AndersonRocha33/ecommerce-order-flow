package com.andersonrocha.ecommerce.domain.checkout.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows

class OrderTest {

    @Test
    fun `should calculate order total correctly`() {
        val items = listOf(
            OrderItem("Produto 1", BigDecimal("50.00"), 2),
            OrderItem("Produto 2", BigDecimal("30.00"), 1)
        )

        val order = Order(
            id = UUID.randomUUID(),
            items = items
        )

        val total = order.total()

        assertEquals(BigDecimal("130.00"), total)
    }

    @Test
    fun `should throw exception when order total is less than minimum`() {
        val items = listOf(
            OrderItem("Produto barato", BigDecimal("5.00"), 1)
        )

        assertThrows(
            IllegalArgumentException::class.java
        ) {
            Order(
                id = UUID.randomUUID(),
                items = items
            )
        }
    }

    @Test
    fun `should change status to paid`() {
        val item = OrderItem("Produto", BigDecimal("10.00"), 1)
        val order = Order(items = listOf(item))

        order.markAsPaid()

        assertEquals(OrderStatus.PAID, order.status)
    }

    @Test
    fun `should not ship unpaid order`() {
        val item = OrderItem("Produto", BigDecimal("10.00"), 1)
        val order = Order(items = listOf(item))

        assertThrows<IllegalArgumentException> {
            order.markAsShipped()
        }
    }
}
