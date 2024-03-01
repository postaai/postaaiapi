package com.br.postaaiapi.postaai.useCase.impl

import com.br.postaaiapi.postaai.enum.EnumProcessStatus
import com.br.postaaiapi.postaai.service.OrderService
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageProcessedInput
import com.br.postaaiapi.postaai.useCase.OrderSaveResultUseCase
import org.springframework.stereotype.Service

@Service
class OrderSaveResultUseCaseImpl(
    private val orderService: OrderService
): OrderSaveResultUseCase {
    override fun saveResultOrder(order: OrderMessageProcessedInput) {
        val orderPersisted = orderService.findByOrderId(order.id)
        orderPersisted.resultPack = order.paths
        orderPersisted.processStatus = EnumProcessStatus.FINISHED.getDescription()
        orderService.saveOrder(orderPersisted)
    }
}