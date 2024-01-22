package com.br.postaaiapi.postaai.useCase.impl

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.enum.EnumPaymentStatus
import com.br.postaaiapi.postaai.enum.EnumProcessStatus
import com.br.postaaiapi.postaai.gateway.RabbitMQGateway
import com.br.postaaiapi.postaai.gateway.S3Gateway
import com.br.postaaiapi.postaai.service.OrderService
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageInput
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
    private val s3Gateway: S3Gateway,
    private val rabbitMQGateway: RabbitMQGateway,
    private val orderServiceConverter: OrderServiceConverter
) : OrderUseCase {

    @Value("\${s3.bucket-order}")
    private val bucketOrder: String? = null
    override fun saveOrder(order: OrderBusinessInput): OrderBusinessOutput {

        val orderEntity = OrderEntity(
            idUser = order.idUser,
            template = getTemplate(order.idTemplate, order.fields),
            paymentStatus = EnumPaymentStatus.PENDING.getDescription(),
            processStatus = EnumProcessStatus.PROCESSING.getDescription(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            fields = order.fields ?: emptyMap()
        )

        return orderService.saveOrder(orderEntity)
    }

    override fun saveLogo(logo: MultipartFile, orderId: String) {
        val idLogo = UUID.randomUUID().toString()
        val logoUri = s3Gateway.uploadFile(bucketOrder, logo, idLogo, idLogo)
        val order = orderService.findByOrderId(orderId)
        order.logoUri = logoUri.toString()
        order.updatedAt = LocalDateTime.now()
        orderService.saveOrder(orderServiceConverter.toEntity(order))
    }

    override fun sendOrder(orderId: String) {
        val order = orderService.findByOrderId(orderId)
        order.paymentStatus = EnumPaymentStatus.APPROVED.getDescription()
        order.updatedAt = LocalDateTime.now()
        val orderPersisted = orderService.saveOrder(orderServiceConverter.toEntity(order))
        sendOrderQueue(orderPersisted)

    }

    override fun findAllOrders(pageable: Pageable): Page<OrderBusinessOutput>? {
        return orderService.findAllOrders(pageable)
    }

    override fun findById(id: String): OrderBusinessOutput {
        return orderService.findByOrderId(id)
    }

    private fun sendOrderQueue(orderPersisted: OrderBusinessOutput) {
        if (orderPersisted.paymentStatus != EnumPaymentStatus.APPROVED.getDescription()) {
            throw Exception("Order not approved")
        }

        val message = OrderMessageInput(
            id = orderPersisted.id ?: "",
            templateURI = orderPersisted.template?.uri ?: "",
            fields = orderPersisted.fields,
            logoURI = orderPersisted.logoUri ?: ""
        )
        rabbitMQGateway.sendMessageOrder(message)
    }
}

private fun getTemplate(idTemplate: String, fields: Map<String, String>?): TemplateEntity {
    return TemplateEntity(
        id = idTemplate,
        name = "Template Teste",
        description = "tempete de teste",
        uri = "s3://postaai-templates/template-f9bf1bce-5d50-11ee-8c99-0242ac120002.7z",
        fields = fields.let { fields ?: emptyMap() }
    )
}
