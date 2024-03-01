package com.br.postaaiapi.postaai.useCase.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.enum.EnumPaymentStatus
import com.br.postaaiapi.postaai.enum.EnumProcessStatus
import com.br.postaaiapi.postaai.gateway.RabbitMQGateway
import com.br.postaaiapi.postaai.gateway.S3Gateway
import com.br.postaaiapi.postaai.service.OrderService
import com.br.postaaiapi.postaai.service.TemplateService
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageProcessedInput
import com.br.postaaiapi.postaai.service.converter.OrderServiceConverter
import com.br.postaaiapi.postaai.useCase.OrderUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

@Service
class OrderUseCaseImpl(
    private val orderService: OrderService,
    private val templateService: TemplateService,
    private val s3Gateway: S3Gateway,
    private val rabbitMQGateway: RabbitMQGateway,
    private val orderServiceConverter: OrderServiceConverter
) : OrderUseCase {

    @Value("\${s3.bucket-order}")
    private val bucketOrder: String? = null
    override fun saveOrder(order: OrderBusinessInput): OrderBusinessOutput {

        val orderEntity = OrderEntity(
            idUser = order.idUser,
            idTemplate = getTemplateById(order.idTemplate).let { it.id ?: "" },
            paymentStatus = EnumPaymentStatus.PENDING.getDescription(),
            processStatus = EnumProcessStatus.PROCESSING.getDescription(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            fields = order.fields
        )

        return orderServiceConverter.toBusinessOutput(orderService.saveOrder(orderEntity))
    }

    override fun saveLogo(logo: MultipartFile, orderId: String) {
        val idLogo = UUID.randomUUID().toString()
        val logoUri = s3Gateway.uploadImage(bucketOrder, logo, idLogo, idLogo)
        val order = orderService.findByOrderId(orderId)
        order.logoUri = logoUri.toString()
        order.updatedAt = LocalDateTime.now()
        orderService.saveOrder(order)
    }

    override fun sendOrder(orderId: String) {
        val order = orderService.findByOrderId(orderId)
        order.paymentStatus = EnumPaymentStatus.APPROVED.getDescription()
        order.updatedAt = LocalDateTime.now()
        val orderPersisted = orderService.saveOrder(order)
        sendOrderQueue(orderServiceConverter.toBusinessOutput(orderPersisted))

    }

    override fun findById(id: String): OrderBusinessOutput {
        return orderService.findByOrderId(id).let(orderServiceConverter::toBusinessOutput)
    }

    override fun findByIdUser(idUser: String, pageable: Pageable): Page<OrderBusinessOutput>? {
        return orderService.findIdUser(idUser, pageable)?.map(orderServiceConverter::toBusinessOutput)
    }

    private fun sendOrderQueue(orderPersisted: OrderBusinessOutput) {
        if (orderPersisted.paymentStatus != EnumPaymentStatus.APPROVED.getDescription()) {
            throw Exception("Order not approved")
        }

        val template = getTemplateById(orderPersisted.idTemplate)

        val message = OrderMessageInput(
            id = orderPersisted.id ?: "",
            templateURI = template.uri ?: "",
            fields = orderPersisted.fields,
            logoURI = orderPersisted.logoUri ?: ""
        )
        rabbitMQGateway.sendMessageOrder(message)
    }

    private fun getTemplateById(idTemplate: String): TemplateEntity {
        return templateService.findTemplateById(idTemplate)
    }
}
