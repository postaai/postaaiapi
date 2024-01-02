package com.br.postaaiapi.postaai.controller

data class OrderRequest(
     val idUser: String,
     val paymentStatus: String,
     val processStatus: String
)
