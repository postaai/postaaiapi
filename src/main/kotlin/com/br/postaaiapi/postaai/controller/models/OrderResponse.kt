package com.br.postaaiapi.postaai.controller.models

import com.br.postaaiapi.postaai.entity.ResultPack
import com.br.postaaiapi.postaai.entity.TemplateEntity
import java.time.LocalDateTime

data class OrderResponse(

    val id: String?,
    val idUser: String?,
    val template: TemplateEntity?,
    val logoUri: String?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val paymentStatus: String? = null,
    val resultPack: List<ResultPack>? = null,
    val fields: Map<String, String>? = null,
    val processStatus: String? = null

)
