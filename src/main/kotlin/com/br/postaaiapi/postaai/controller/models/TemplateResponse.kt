package com.br.postaaiapi.postaai.controller.models

import java.io.Serializable
import java.time.LocalDateTime

data class TemplateResponse(

        val id: String? = null,
        val name: String,
        val description: String,
        val uri: String? = null,
        val fields: List<String>,
        val createdAt: LocalDateTime? = null,
        val updatedAt: LocalDateTime? = null,
        val exampleImages: List<String>? = null
): Serializable
