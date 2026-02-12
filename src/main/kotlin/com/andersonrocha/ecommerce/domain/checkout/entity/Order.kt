package com.andersonrocha.ecommerce.domain.checkout.entity

import java.math.BigDecimal     // BigDecimal é usado para valores monetários, evitando problemas de precisão (ex: Double)
import java.util.UUID     // UUID representa um identificador único universal, ideal para identificar pedidos sem depender do banco

class Order(
    val id: UUID = UUID.randomUUID(),
    val items: List<OrderItem>
) {

    var status: OrderStatus = OrderStatus.CREATED
        private set

    companion object {
        private val MIN_ORDER_VALUE = BigDecimal("10.00")
    }

    init {
        require(items.isNotEmpty()) { "Pedido deve conter ao menos um item" }
        validateMinimumValue()
    }

    fun total(): BigDecimal =
        items.fold(BigDecimal.ZERO) { acc, item -> acc + item.subtotal() }

    fun markAsPaid() {
        require(status == OrderStatus.CREATED) {
            "Somente pedidos criados podem ser pagos"
        }
        status = OrderStatus.PAID
    }

    fun markAsShipped() {
        require(status == OrderStatus.PAID) {
            "Somente pedidos pagos podem ser enviados"
        }
        status = OrderStatus.SHIPPED
    }

    private fun validateMinimumValue() {
        require(total() >= MIN_ORDER_VALUE) {
            "Valor mínimo do pedido é R$ $MIN_ORDER_VALUE"
        }
    }
}
