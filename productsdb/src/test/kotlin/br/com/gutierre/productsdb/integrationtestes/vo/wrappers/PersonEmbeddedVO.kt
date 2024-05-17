package br.com.gutierre.productsdb.integrationtestes.vo.wrappers

import br.com.gutierre.productsdb.data.vo.v1.PersonVO
import com.fasterxml.jackson.annotation.JsonProperty

class PersonEmbeddedVO {

    @JsonProperty("personVOList")
    var persons: List<PersonVO>? = null
}