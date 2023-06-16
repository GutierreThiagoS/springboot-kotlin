package br.com.gutierre.productsdb.exceptions

import java.util.Date

class ExceptionResponse(
    val status: Boolean = false,
    val message: String?,
    val details: String,
    val timestamp: Date = Date()
)