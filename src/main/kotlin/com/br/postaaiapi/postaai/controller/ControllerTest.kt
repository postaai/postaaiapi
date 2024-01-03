package com.br.postaaiapi.postaai.controller

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.entity.ResultPack
import com.br.postaaiapi.postaai.useCase.SendOrderAndSave
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/order")
class ControllerTest(
    private val sendOrderAndSave: SendOrderAndSave
) {

    @PostMapping("/sendOrder")
    fun sendOrder(@RequestBody orderRequest: OrderRequest): OrderEntity{
        val orderEntity = OrderEntity(
            idUser = orderRequest.idUser,
            idTemplate = "f9bf1bce-5d50-11ee-8c99-0242ac120002",
            pathLogo = "70411724-5d66-11ee-8c99-0242ac120002/logo.png",
            fields = orderRequest.fields
           // paymentStatus = orderRequest.paymentStatus,
           // processStatus = orderRequest.processStatus,
           // resultPack = Collections.singletonList(ResultPack(UUID.randomUUID(), "123"))
        )

        val result = sendOrderAndSave.sendMessage(orderEntity)

        return result

    }
    @GetMapping
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("funcionou.....")
    }

}