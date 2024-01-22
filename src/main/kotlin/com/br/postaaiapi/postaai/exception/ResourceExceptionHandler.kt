package com.br.postaaiapi.postaai.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*
import kotlin.collections.HashMap

private const val VALIDATION_ERROR = "Validation error"

private const val FILE_ERROR = "File error"

private const val ERROR_DEFAULT = "ERROR"

private const val PAYMENT_MESSAGE = "Payment not approved"

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException::class)
    fun validationError(
        error: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError<Map<String, String>>> {

        val errors = HashMap<String, String>()

        error.fieldErrors.forEach { fieldError ->
            errors[fieldError.field] = fieldError.defaultMessage ?: "Invalid value"
        }


        return ResponseEntity.badRequest().body(
            StandardError(
                timestamp = Calendar.getInstance().timeInMillis,
                status = HttpStatus.BAD_REQUEST.value(),
                error = errors,
                message = VALIDATION_ERROR,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(FileException::class)
    fun fileException(exception: FileException, request: HttpServletRequest): ResponseEntity<StandardError<String>> {
        return ResponseEntity.badRequest().body(
            StandardError(
                timestamp = Calendar.getInstance().timeInMillis,
                status = HttpStatus.BAD_REQUEST.value(),
                error = exception.message ?: FILE_ERROR,
                message = FILE_ERROR,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(exception: HttpMessageNotReadableException, request: HttpServletRequest): ResponseEntity<StandardError<String>> {
        return ResponseEntity.badRequest().body(
            StandardError(
                timestamp = Calendar.getInstance().timeInMillis,
                status = HttpStatus.BAD_REQUEST.value(),
                error = exception.message ?: "Invalid value",
                message = ERROR_DEFAULT,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(ObjectNotFoundException::class)
    fun objectNotFoundException(exception: ObjectNotFoundException, request: HttpServletRequest): ResponseEntity<StandardError<String>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            StandardError(
                timestamp = Calendar.getInstance().timeInMillis,
                status = HttpStatus.NOT_FOUND.value(),
                error = exception.message ?: ERROR_DEFAULT,
                message = ERROR_DEFAULT,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(PaymentException::class)
    fun paymentException(exception: PaymentException, request: HttpServletRequest): ResponseEntity<StandardError<String>> {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(
            StandardError(
                timestamp = Calendar.getInstance().timeInMillis,
                status = HttpStatus.PAYMENT_REQUIRED.value(),
                error = exception.message ?: PAYMENT_MESSAGE,
                message = PAYMENT_MESSAGE,
                path = request.requestURI
            )
        )
    }

}