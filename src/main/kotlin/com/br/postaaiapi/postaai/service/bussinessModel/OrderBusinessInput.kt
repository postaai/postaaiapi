package com.br.postaaiapi.postaai.service.bussinessModel

import java.io.Serializable

data class OrderBusinessInput(
    val idUser: String,
    val idTemplate: String,
    val fields: Map<String, String>? = null
) : Serializable



