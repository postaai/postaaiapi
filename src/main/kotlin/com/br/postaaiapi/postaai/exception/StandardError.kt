package com.br.postaaiapi.postaai.exception

data class StandardError<T>(
    val timestamp: Long,
    val status: Int,
    val error: T,
    val message: String,
    val path: String
)
