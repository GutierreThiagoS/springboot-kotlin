package br.com.gutierre.productsdb.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseAnswerQuestion(
        @field:JsonProperty("pergunta_titulo")
        val title: String,

        @field:JsonProperty("pergunta_desc")
        val description: String,

        @field:JsonProperty("resposta")
        val answer: String,

        @field:JsonProperty("data_resposta")
        val responseDate: String,

        @field:JsonProperty("respondido_por")
        val userAnswer: String,

        val info: String = "Resposta salva com sucesso!",
        val status: Boolean = true,
)
