package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class RequestAnswerQuestion(

        @field:JsonProperty("codigo_pergunta")
        var questionId: Long = 0,

        @field:JsonProperty("codigo_opcao")
        var questionItemId: Long = 0,

        @field:JsonProperty("codigo_usuario")
        var userId: Long = 0,
)
