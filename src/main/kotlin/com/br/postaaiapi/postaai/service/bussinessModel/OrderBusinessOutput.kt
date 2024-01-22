package com.br.postaaiapi.postaai.service.bussinessModel

import com.br.postaaiapi.postaai.entity.ResultPack
import com.br.postaaiapi.postaai.entity.TemplateEntity
import java.io.Serializable
import java.time.LocalDateTime

data class OrderBusinessOutput(

    val id: String?,
    val idUser: String?,
    val template: TemplateEntity?,
    var logoUri: String?,
    val createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var paymentStatus: String? = null,
    val resultPack: List<ResultPack>? = null,
    val fields: Map<String, String>? = null,
    val processStatus: String? = null
) : Serializable