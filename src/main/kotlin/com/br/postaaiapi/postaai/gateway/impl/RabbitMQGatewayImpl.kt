package com.br.postaaiapi.postaai.gateway.impl

import com.br.postaaiapi.postaai.gateway.RabbitMQGateway
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageProcessedInput
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class RabbitMQGatewayImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val sendOrderQueue: Queue,
): RabbitMQGateway {

    override fun sendMessageOrder(message: OrderMessageInput) {
        rabbitTemplate.convertAndSend(sendOrderQueue.name, message)
        println("Mensagem enviada para a : ${message}")

    }

    @RabbitListener(queues = ["\${spring.rabbitmq.queues.order-processed-queue}"])
    override fun listenMessageOrder(@Payload message: OrderMessageProcessedInput) {
        println("Mensagem recebida: ${message}")

    }
}