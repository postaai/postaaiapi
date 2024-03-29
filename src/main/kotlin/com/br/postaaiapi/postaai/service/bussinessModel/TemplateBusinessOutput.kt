package com.br.postaaiapi.postaai.service.bussinessModel

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

data class TemplateBusinessOutput(

    val id: String? = null,
    val name: String,
    val description: String,
    var uri: String? = null,
    val fields: List<String>,
    val price: BigDecimal,
    val category: String,
    val createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var exampleImages: List<String>? = null,
    val exampleThumbnails: List<String>? = null
) : Serializable
