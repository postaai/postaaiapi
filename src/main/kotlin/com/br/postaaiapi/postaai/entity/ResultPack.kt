package com.br.postaaiapi.postaai.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*

data class ResultPack(

    @JsonProperty("id")
    private val id: UUID,

    @JsonProperty("path")
    private val path: String
): Serializable
