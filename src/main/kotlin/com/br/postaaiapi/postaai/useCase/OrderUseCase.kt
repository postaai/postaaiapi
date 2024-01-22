package com.br.postaaiapi.postaai.useCase

import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile


interface OrderUseCase {

    fun saveOrder(order: OrderBusinessInput): OrderBusinessOutput

    fun saveLogo(logo: MultipartFile, orderId: String)

    fun sendOrder(orderId: String)

    fun findAllOrders(pageable: Pageable): Page<OrderBusinessOutput>?

    fun findById(id: String): OrderBusinessOutput
}