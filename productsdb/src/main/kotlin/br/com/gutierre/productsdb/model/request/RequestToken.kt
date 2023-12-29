package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestToken(
    @field:JsonProperty("token")
    val crypto: String = ""
)