package br.com.gutierre.productsdb.model.response

import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseSaveQuestion(

        @field:JsonProperty("pergunta")
        val question: Question,

        @field:JsonProperty("opcoes")
        val items: List<ItemQuestion>,

        val info: String = "Perguntas e opções salvas com sucesso!",
        val status: Boolean = true,
)
