package com.br.postaaiapi.postaai.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class OrderEntity(

    @Id
     val id: String? = null,

     val idUser: String,

     val templateUri: String,

     val logoUri: String,

     val fields: Map<String, String>

   // private val paymentStatus: String,

    //private val resultPack: List<ResultPack>,

    //private val processStatus: String
): Serializable
