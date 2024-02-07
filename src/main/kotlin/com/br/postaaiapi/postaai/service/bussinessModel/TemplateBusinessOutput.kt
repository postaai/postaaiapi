package com.br.postaaiapi.postaai.service.bussinessModel

import java.io.Serializable
import java.time.LocalDateTime

data class TemplateBusinessOutput(

        val id: String? = null,
        val name: String,
        val description: String,
        var uri: String? = null,
        val fields: List<String>,
        val createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null,
        var exampleImages: List<String>? = null
): Serializable
