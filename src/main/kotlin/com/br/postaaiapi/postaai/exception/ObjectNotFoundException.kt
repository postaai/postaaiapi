package com.br.postaaiapi.postaai.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

private const val OBJECT_NOT_FOUND = "Object not found"

@ResponseStatus(HttpStatus.NOT_FOUND)
class ObjectNotFoundException(message: String? = null) : RuntimeException(message ?: OBJECT_NOT_FOUND) {

}