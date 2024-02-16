package com.br.postaaiapi.postaai.service.bussinessModel

import java.math.BigDecimal

data class TemplateBusinessInput(
        val name: String,
        val description: String,
        val price: BigDecimal,
        val fields: List<String>
)
