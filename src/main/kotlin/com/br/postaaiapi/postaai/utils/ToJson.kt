package com.br.postaaiapi.postaai.utils

import com.google.gson.Gson

fun <T> T.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}