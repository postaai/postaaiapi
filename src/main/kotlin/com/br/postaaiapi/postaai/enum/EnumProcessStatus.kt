package com.br.postaaiapi.postaai.enum

enum class EnumProcessStatus(
    private val code: Int,
    private val description: String
) {
    PROCESSING(1, "Processing"),
    FINISHED(2, "Finished"),
    ERROR(3, "Error");

    fun getCode(): Int {
        return code
    }

    fun getDescription(): String {
        return description
    }

    fun getEnumByCode(code: Int): EnumProcessStatus {
        return entries.first { it.code == code }
    }
}