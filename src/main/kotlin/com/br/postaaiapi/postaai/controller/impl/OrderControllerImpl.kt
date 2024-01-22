package com.br.postaaiapi.postaai.controller.impl

import com.br.postaaiapi.postaai.controller.OrderController
import com.br.postaaiapi.postaai.controller.convert.OrderControllerConvert
import com.br.postaaiapi.postaai.controller.models.OrderRequest
import com.br.postaaiapi.postaai.controller.models.OrderResponse
import com.br.postaaiapi.postaai.useCase.OrderUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/order")
class OrderControllerImpl(
    private val orderUseCase: OrderUseCase,
    private val orderControllerConvert: OrderControllerConvert
) : OrderController {
    override fun createOrder(orderRequest: OrderRequest): ResponseEntity<OrderResponse> {
        val order = orderUseCase.saveOrder(orderControllerConvert.toBusiness(orderRequest))
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(order.id)
                .toUri()
        return ResponseEntity.created(uri).body(orderControllerConvert.toResponse(order))
    }

    override fun insertLogo(file: MultipartFile, orderID: String): ResponseEntity<Any> {
        orderUseCase.saveLogo(file, orderID)
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(orderID)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    override fun approveOrder(orderID: String): ResponseEntity<Any> {
        orderUseCase.sendOrder(orderID)
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(orderID)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    override fun getOrder(orderID: String): ResponseEntity<OrderResponse> {
        val order = orderUseCase.findById(orderID)
        return ResponseEntity.ok(orderControllerConvert.toResponse(order))
    }

    override fun getAllOrders(pageable: Pageable): ResponseEntity<Page<OrderResponse>> {
        val orders = orderUseCase.findAllOrders(pageable)
        return ResponseEntity.ok(orders?.map(orderControllerConvert::toResponse))
    }

}