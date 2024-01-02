package com.br.postaaiapi.postaai.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class OrderEntity(

    @Id
    @JsonProperty("id")
    private val id: String? = null,

    @JsonProperty("idUser")
    private val idUser: String,

    @JsonProperty("idTemplate")
    private val idTemplate: String,

    @JsonProperty("pathLogo")
    private val pathLogo: String,

    @JsonProperty("paymentStatus")
    private val paymentStatus: String,

    @JsonProperty("resultPack")
    private val resultPack: List<ResultPack>,

    @JsonProperty("processStatus")
    private val processStatus: String
): Serializable
