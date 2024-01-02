package com.br.postaaiapi.postaai.service

import com.br.postaaiapi.postaai.entity.OrderEntity
import org.springframework.transaction.annotation.Transactional

interface OrderService {

    @Transactional
    fun saveOrder(order: OrderEntity): OrderEntity

    fun findByOrderId(id: String): OrderEntity
}