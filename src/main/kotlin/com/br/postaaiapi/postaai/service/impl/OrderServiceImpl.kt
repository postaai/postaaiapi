package com.br.postaaiapi.postaai.service.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.repository.OrderRepository
import com.br.postaaiapi.postaai.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
): OrderService {
    override fun saveOrder(order: OrderEntity): OrderEntity {
        return orderRepository.save(order)
    }

    override fun findByOrderId(id: String): OrderEntity {
        return orderRepository.findById(id).orElseThrow()
    }
}