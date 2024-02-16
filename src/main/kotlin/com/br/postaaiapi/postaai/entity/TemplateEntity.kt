package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Document
data class TemplateEntity(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    var uri: String? = null,
    val fields: List<String>,
    val price: BigDecimal,
    val createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var exampleImages: List<String>? = null
): Serializable
