package com.br.postaaiapi.postaai.service.bussinessModel

data class TemplateBusinessInput(
        val name: String,
        val description: String,
        val fields: List<String>
)
