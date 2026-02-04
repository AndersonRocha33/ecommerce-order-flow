package com.andersonrocha.ecommerce.domain.checkout.entity

import java.math.BigDecimal

data class OrderItem(
    val productId: String,
    val price: BigDecimal,
    val quantity: Int
) {

    init {
        require(quantity > 0) { "Quantidade deve ser maior que zero" }
        require(price >= BigDecimal.ZERO) { "Preço não pode ser negativo" }
    }

    fun subtotal(): BigDecimal =
        price.multiply(quantity.toBigDecimal())
}
