package br.com.gutierre.productsdb.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SaveItemQuestion(
        @field:JsonProperty("descricao")
        var description: String = "",

        @field:JsonProperty("posicao")
        var position: Int = 0
)
