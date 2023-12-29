package br.com.gutierre.productsdb.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ResponseSingIn(
    @field:JsonProperty("info")
    val info: String,
    val authenticated: Boolean = false
)
