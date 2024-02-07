package com.br.postaaiapi.postaai.controller.models

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TemplateRequest(
    @field:NotBlank(message = "Nome do template não pode ser vazio")
    @field:Schema(description = "Nome do template", example = "Template 1")
    val name: String,

    @field:NotBlank(message = "Descrição do template não pode ser vazio")
    @field:Schema(description = "Descrição do template", example = "Template para gerar packs")
    val description: String,

    @field:Schema(description = "Campos do template", example = "[\"nome\", \"idade\"]")
    @field:NotNull(message = "Campos do template não pode ser vazio")
    val fields: List<String>
)