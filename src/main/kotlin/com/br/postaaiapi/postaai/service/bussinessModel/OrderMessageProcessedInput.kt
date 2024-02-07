package com.br.postaaiapi.postaai.service.bussinessModel

data class OrderMessageProcessedInput(
    val id: String,
    val templateURI: String,
    val logoURI: String,
    val fields: Map<String, String>? = null
)