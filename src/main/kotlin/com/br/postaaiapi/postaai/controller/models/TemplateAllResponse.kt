package com.br.postaaiapi.postaai.controller.models

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

data class TemplateAllResponse(

        val id: String? = null,
        val name: String,
        val price: BigDecimal,
        val category: String,
        val createdAt: LocalDateTime? = null,
        val exampleThumbnails: List<String>? = null
): Serializable
