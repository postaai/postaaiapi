package com.br.postaaiapi.postaai.service.bussinessModel

data class TemplateMetadata(
    val textFields: Map<String, String>? = null,
    val logoDimensions: LogoDimensions
)
