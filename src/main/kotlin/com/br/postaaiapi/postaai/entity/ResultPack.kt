package com.br.postaaiapi.postaai.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*

data class ResultPack(

    private val id: UUID,
    private val path: String
)
