package com.br.postaaiapi.postaai.gateway.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.gateway.RabbitMQGateway
import com.br.postaaiapi.postaai.utils.toJson
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class RabbitMQGatewayImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val sendOrderQueue: Queue,
): RabbitMQGateway {

    override fun sendMessageOrder(message: OrderEntity) {
        rabbitTemplate.convertAndSend(sendOrderQueue.name, message.toJson())
        println("Mensagem enviada para a : ${message.toJson()}")
    }
}