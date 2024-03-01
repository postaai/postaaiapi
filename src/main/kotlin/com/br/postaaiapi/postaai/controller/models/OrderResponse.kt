package com.br.postaaiapi.postaai.controller.models

import com.br.postaaiapi.postaai.entity.PathImages
import com.br.postaaiapi.postaai.entity.TemplateEntity
import java.time.LocalDateTime

data class OrderResponse(

    val id: String?,
    val idUser: String?,
    val idTemplate: String,
    val logoUri: String?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val paymentStatus: String? = null,
    val resultPack: List<PathImages>? = null,
    val fields: Map<String, String>? = null,
    val processStatus: String? = null

)
