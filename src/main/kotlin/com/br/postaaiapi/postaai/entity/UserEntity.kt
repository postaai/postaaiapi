package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserEntity(

    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String?,
    val cpf: String,
    val phone: String?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
