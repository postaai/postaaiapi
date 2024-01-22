package com.br.postaaiapi.postaai.service.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.exception.ObjectNotFoundException
import com.br.postaaiapi.postaai.repository.OrderRepository
import com.br.postaaiapi.postaai.service.OrderService
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import com.br.postaaiapi.postaai.service.converter.OrderServiceConverter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.net.URI

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderConverter: OrderServiceConverter
) : OrderService {
    override fun saveOrder(order: OrderEntity): OrderBusinessOutput {
        return orderConverter.toBusinessOutput(orderRepository.save(order))
    }

    override fun findByOrderId(id: String): OrderBusinessOutput {
        return orderRepository.findById(id)
            .orElseThrow { ObjectNotFoundException() }
            .let(orderConverter::toBusinessOutput)
    }

    override fun saveLogo(idOrder: String, logoUri: URI) {
        val order = orderRepository.findById(idOrder)
            .orElseThrow { ObjectNotFoundException() }
        order.logoUri = logoUri.toString()
        orderRepository.save(order)
    }

    override fun findAllOrders(pageable: Pageable): Page<OrderBusinessOutput>? {
        return orderRepository.findAll(pageable).map(orderConverter::toBusinessOutput)
    }

}