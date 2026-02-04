package com.andersonrocha.ecommerce.domain.checkout.entity

import java.math.BigDecimal     // BigDecimal é usado para valores monetários, evitando problemas de precisão (ex: Double)
import java.util.UUID     // UUID representa um identificador único universal, ideal para identificar pedidos sem depender do banco

class Order(
    val id: UUID = UUID.randomUUID(),     // Identificador único do pedido. Caso não seja informado, um UUID aleatório é gerado automaticamente
    val items: List<OrderItem>     // Lista de itens do pedido. Um pedido é composto por um ou mais OrderItem
) {

    companion object {
        private val MIN_ORDER_VALUE = BigDecimal("10.00")     // Valor mínimo permitido para um pedido. Fica no companion object por ser uma regra da classe (estática)
    }

    init {
        require(items.isNotEmpty()) { "Pedido deve conter ao menos um item" }     // Garante que o pedido não seja criado sem itens. Se a lista estiver vazia, lança IllegalArgumentException
        validateMinimumValue()     // Valida se o valor total do pedido respeita o valor mínimo definido
    }

    fun total(): BigDecimal =     // Calcula o valor total do pedido somando o subtotal de cada item
        items.fold(BigDecimal.ZERO) { acc, item -> acc + item.subtotal() }     // fold percorre a lista acumulando um valor. BigDecimal.ZERO é o valor inicial do acumulador. acc representa o total acumulado até o momento. item.subtotal() retorna o valor total daquele item

    private fun validateMinimumValue() {     // Validação interna da regra de valor mínimo do pedido
        require(total() >= MIN_ORDER_VALUE) {     // Exige que o total do pedido seja maior ou igual ao valor mínimo
            "Valor mínimo do pedido é R$ $MIN_ORDER_VALUE"     // Mensagem exibida caso a regra seja violada
        }
    }
}
