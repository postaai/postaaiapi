package com.br.postaaiapi.postaai.service

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.net.URI

interface OrderService {

    @Transactional
    fun saveOrder(order: OrderEntity): OrderEntity

    fun findByOrderId(id: String): OrderEntity
    fun saveLogo(idOrder: String, logoUri: URI)

    fun findAllOrders(pageable: Pageable): Page<OrderEntity>
}