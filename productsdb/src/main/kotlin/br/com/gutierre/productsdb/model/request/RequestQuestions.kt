package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestQuestions(
        @field:JsonProperty("codigo_usuario")
        val userId: Long,

        @field:JsonProperty("limite")
        val limit: Int
 )
