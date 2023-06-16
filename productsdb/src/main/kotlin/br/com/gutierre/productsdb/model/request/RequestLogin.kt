package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestLogin(

    val email: String,

    @field:JsonProperty("senha")
    val password: String
)
