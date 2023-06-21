package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestSaveQuestion(

        @field:JsonProperty("codigo_admin")
        val adminId: Long,

        @field:JsonProperty("usuario_responder")
        val userForQuestionId: Long,

        @field:JsonProperty("titulo")
        val title: String,

        @field:JsonProperty("descricao")
        val description: String,

        @field:JsonProperty("opcoes")
        val items: List<SaveItemQuestion>,
)
