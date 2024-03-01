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
    val idTemplate: String,
    var logoUri: String? = null,
    val createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var paymentStatus: String? = null,
    var resultPack: List<PathImages>? = null,
    val fields: Map<String, String>? = null,
    var processStatus: String? = null
) : Serializable
