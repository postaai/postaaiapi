package com.br.postaaiapi.postaai.enum

enum class EnumCategory(
    private val code: Int,
    private val description: String
) {

    ACAI(1, "Açaí"),
    BURGER(2, "Burger"),
    PIZZA(3, "Pizza"),
    DRINK(4, "Drink");

    fun getEnumByCode(code: Int): EnumCategory {
        return EnumCategory.entries.first { it.code == code }
    }

    fun getCode(): Int {
        return code
    }

    fun getDescription(): String {
        return description
    }

}