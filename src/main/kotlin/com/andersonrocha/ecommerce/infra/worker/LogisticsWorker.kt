package com.andersonrocha.ecommerce.infra.worker

import com.andersonrocha.ecommerce.domain.checkout.gateway.OrderQueue

class LogisticsWorker(
    private val orderQueue: OrderQueue
) {

    fun start() {
        println("🚚 Worker de Logística iniciado...")

        val order = orderQueue.consume()

        if (order != null) {
            println("📦 Iniciando envio do pedido ${order.id}")
        } else {
            println("📭 Nenhuma mensagem na fila.")
        }
    }
}