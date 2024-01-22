package com.br.postaaiapi.postaai.gateway

import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageInput

interface RabbitMQGateway {

    fun sendMessageOrder(message: OrderMessageInput)
}