package com.br.postaaiapi.postaai.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class OrderEntity(

    @Id
    private val id: String? = null,

    private val idUser: String,

    private val idTemplate: String,

    private val pathLogo: String,

    private val fields: Map<String, String>

   // private val paymentStatus: String,

    //private val resultPack: List<ResultPack>,

    //private val processStatus: String
)
