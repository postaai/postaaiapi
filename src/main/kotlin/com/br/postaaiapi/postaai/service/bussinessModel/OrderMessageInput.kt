package com.br.postaaiapi.postaai.service.bussinessModel

data class OrderMessageInput(
    val id: String,
    val templateUri: String,
    val logoUri: String,
    val templateMetadata: TemplateMetadata,
)