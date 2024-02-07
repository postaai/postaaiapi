package com.br.postaaiapi.postaai.service.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.exception.ObjectNotFoundException
import com.br.postaaiapi.postaai.repository.OrderRepository
import com.br.postaaiapi.postaai.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.net.URI

private const val ORDER_NAO_ENCONTRADA = "Order não encontrada"

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun saveOrder(order: OrderEntity): OrderEntity {
        return orderRepository.save(order)
    }

    override fun findByOrderId(id: String): OrderEntity {
        return orderRepository.findById(id)
            .orElseThrow { ObjectNotFoundException(ORDER_NAO_ENCONTRADA) }

    }

    override fun saveLogo(idOrder: String, logoUri: URI) {
        val order = orderRepository.findById(idOrder)
            .orElseThrow { ObjectNotFoundException(ORDER_NAO_ENCONTRADA) }
        order.logoUri = logoUri.toString()
        orderRepository.save(order)
    }

    override fun findAllOrders(pageable: Pageable): Page<OrderEntity> {
        return orderRepository.findAll(pageable)
    }

}