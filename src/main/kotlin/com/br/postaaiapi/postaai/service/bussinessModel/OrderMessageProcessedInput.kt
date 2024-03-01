package com.br.postaaiapi.postaai.service.bussinessModel

import com.br.postaaiapi.postaai.entity.PathImages

data class OrderMessageProcessedInput(
    val id: String,
    val paths: List<PathImages>,
    val status: String
)