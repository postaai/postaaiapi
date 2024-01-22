package com.br.postaaiapi.postaai.enum

enum class EnumPaymentStatus(
    private val code: Int,
    private val description: String
) {

    PENDING(1, "Pending"),
    APPROVED(2, "Approved"),
    REPROVED(3, "Reproved");

    fun getEnumByCode(code: Int): EnumPaymentStatus {
        return entries.first { it.code == code }
    }

    fun getCode(): Int {
        return code
    }

    fun getDescription(): String {
        return description
    }

}



