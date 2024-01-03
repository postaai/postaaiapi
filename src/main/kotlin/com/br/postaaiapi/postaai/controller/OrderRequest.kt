package com.br.postaaiapi.postaai.controller

import java.io.Serializable

data class OrderRequest(
     val idUser: String,
     val fields: Map<String, String>
): Serializable
