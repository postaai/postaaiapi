package com.br.postaaiapi.postaai.gateway

import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageProcessedInput

interface RabbitMQGateway {

    fun sendMessageOrder(message: OrderMessageInput)

    fun listenMessageOrder(message: OrderMessageProcessedInput)
}