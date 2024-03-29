package com.br.postaaiapi.postaai.controller.models

import com.br.postaaiapi.postaai.enum.EnumCategory
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

data class TemplateResponse(

        val id: String? = null,
        val name: String,
        val description: String,
        val uri: String? = null,
        val fields: List<String>,
        val price: BigDecimal,
        val createdAt: LocalDateTime? = null,
        val updatedAt: LocalDateTime? = null,
        val exampleImages: List<String>? = null,
        val exampleThumbnails: List<String>? = null
): Serializable
