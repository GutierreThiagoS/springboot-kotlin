package br.com.gutierre.productsdb.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "first_name", "last_name", "address")
data class PersonVO (

    @Mapping("id")
    @field:JsonProperty("id")
    var id: Long = 0,

    @field:JsonProperty("first_name")
    var firstName: String = "",

    @field:JsonProperty("last_name")
    var lastName: String = "",

    var address: String = "",

    @field:JsonIgnore
    var gender: String = "",

    @field:JsonIgnore
    var enabled: Boolean = false
): RepresentationModel<PersonVO>()
