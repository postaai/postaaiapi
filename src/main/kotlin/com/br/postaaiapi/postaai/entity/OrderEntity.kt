package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime

@Document
data class OrderEntity(

    @Id
    val id: String? = null,
    val idUser: String,
    val template: TemplateEntity,
    var logoUri: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val paymentStatus: String? = null,
    val resultPack: List<ResultPack>? = null,
    val fields: Map<String, String>? = null,
    val processStatus: String? = null
) : Serializable
