package com.br.postaaiapi.postaai.controller

import com.br.postaaiapi.postaai.controller.models.OrderRequest
import com.br.postaaiapi.postaai.controller.models.OrderResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "Order", description = "Order API")
interface OrderController {

    @Operation(summary = "Create Order", description = "Create Order for generate packs")
    @PostMapping("/create")
    fun createOrder(@RequestBody @Valid orderRequest: OrderRequest): ResponseEntity<OrderResponse>

    @Operation(summary = "Insert Logo", description = "Insert Logo by Order ID")
    @PostMapping("/insertLogo")
    fun insertLogo(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("orderID") orderID: String
    ): ResponseEntity<Any>

    @Operation(summary = "Approve Order", description = "Approve Order and Send to queue Order by Order ID")
    @PatchMapping("/approve")
    fun approveOrder(@RequestParam("orderID") orderID: String): ResponseEntity<Any>

    @Operation(summary = "Get Order", description = "Get Order by Order ID")
    @GetMapping("/get/{orderID}")
    fun getOrder(@PathVariable("orderID") orderID: String): ResponseEntity<OrderResponse>

    @Operation(summary = "Get All Orders", description = "Get All Orders")
    @GetMapping("/getAll")
    fun getAllOrders(pageable: Pageable): ResponseEntity<Page<OrderResponse>>
}