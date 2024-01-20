package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime

@Document
data class TemplateEntity(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val uri: String,
    val fields: Map<String, String>,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,
): Serializable
