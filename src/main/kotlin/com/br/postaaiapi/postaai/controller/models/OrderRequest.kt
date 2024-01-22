package com.br.postaaiapi.postaai.controller.models

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(name = "OrderRequest", description = "Order Request")
data class OrderRequest(

    @field:NotBlank(message = "Id do usuário não pode ser vazio")
    @field:Schema(description = "Id do usuário", example = "123")
    val idUser: String,

    @field:NotBlank(message = "Id do template não pode ser vazio")
    @field:Schema(description = "Id do template", example = "123")
    val idTemplate: String,

    @field:Schema(description = "Campos do template", example = "{\"nome\": \"João\"}")
    val fields: Map<String, String>? = null

)
