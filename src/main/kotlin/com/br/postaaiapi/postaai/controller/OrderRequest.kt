package com.br.postaaiapi.postaai.controller

data class OrderRequest(
     val idUser: String,
     val fields: Map<String, String>
)
