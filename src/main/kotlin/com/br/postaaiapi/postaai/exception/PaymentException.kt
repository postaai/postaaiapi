package com.br.postaaiapi.postaai.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

private const val PAYMENT_NOT_APPROVED = "payment not approved"

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
class PaymentException : RuntimeException(PAYMENT_NOT_APPROVED) {
}