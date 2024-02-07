package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime

@Document
data class TemplateEntity(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val uri: String? = null,
    val fields: List<String>,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val exampleImages: List<String>? = null
): Serializable
