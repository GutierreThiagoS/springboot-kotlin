package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestRegister(

        @field:JsonProperty("nome")
        val name: String,

        @field:JsonProperty("email")
        val email: String,

        @field:JsonProperty("senha")
        val password: String,
//
//        @field:JsonProperty("admin")
//        val admin: Boolean = false,
)
