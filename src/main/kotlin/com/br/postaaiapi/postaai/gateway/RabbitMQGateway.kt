package com.br.postaaiapi.postaai.gateway

import com.br.postaaiapi.postaai.entity.OrderEntity

interface RabbitMQGateway {

    fun sendMessageOrder(message: OrderEntity)
}