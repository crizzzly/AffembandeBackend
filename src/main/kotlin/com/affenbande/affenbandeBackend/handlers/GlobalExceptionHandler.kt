package com.affenbande.affenbandeBackend.handlers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): ResponseEntity<String> {
        logger.error("An error occurred: ${ex.message}", ex)
        return ResponseEntity("An error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}