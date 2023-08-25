package br.com.gutierre.productsdb.model.response

import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseQuestions(

    @field:JsonProperty("nome_usuario")
    var name: String,

    @field:JsonProperty("permissao")
    var permission: String,

    @field:JsonProperty("pergunta")
    var question: Question,

    @field:JsonProperty("opcoes")
    var items: List<ItemQuestion>
)
