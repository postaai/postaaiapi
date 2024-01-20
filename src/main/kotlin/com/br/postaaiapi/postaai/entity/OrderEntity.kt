package com.br.postaaiapi.postaai.entity

import com.br.postaaiapi.postaai.enum.EnumPaymentStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime

@Document
data class OrderEntity(

    @Id
    val id: String? = null,
    val idUser: String,
    val template: TemplateEntity,
    val templateUri: String,
    val logoUri: String,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,
    val paymentStatus: EnumPaymentStatus? = null,
    val resultPack: List<ResultPack>? = null,
    val processStatus: String? = null
) : Serializable
