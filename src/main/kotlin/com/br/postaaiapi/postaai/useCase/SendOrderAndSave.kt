package com.br.postaaiapi.postaai.useCase

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.gateway.RabbitMQGateway
import com.br.postaaiapi.postaai.service.OrderService
import org.springframework.stereotype.Service

@Service
class SendOrderAndSave(
    private val orderService: OrderService,
    private val rabbitMQGateway: RabbitMQGateway
) {
    fun sendMessage(order: OrderEntity): OrderEntity{
        val orderPersisted = orderService.saveOrder(order)

        rabbitMQGateway.sendMessageOrder(orderPersisted)
        return orderPersisted
    }
}