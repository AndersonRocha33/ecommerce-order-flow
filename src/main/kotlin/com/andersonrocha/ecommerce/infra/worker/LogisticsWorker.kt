package com.andersonrocha.ecommerce.infra.worker

import com.andersonrocha.ecommerce.domain.checkout.usecase.ProcessShipmentUseCase

class LogisticsWorker(
    private val processShipmentUseCase: ProcessShipmentUseCase
) {

    fun start() {
        println("🚚 Worker de Logística iniciado...")
        processShipmentUseCase.execute()
    }
}
