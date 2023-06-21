package br.com.gutierre.productsdb.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "usuario")
@JsonPropertyOrder("user_id", " nome", "email")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty("user_id")
    var id: Long = 0,

    @field:JsonProperty("nome")
    @Column(name = "nome", nullable = false, length = 200)
    var name: String = "",

    @Column(nullable = false, length = 200)
    var email: String = "",

    @field:JsonIgnore
    @Column(name = "senha", nullable = false, length = 200)
    var password: String = "",

    @field:JsonIgnore
    @Column(name = "cargo", nullable = false)
    var office: Int = 0,

    @field:JsonIgnore
    @Column(name = "data_criacao", nullable = false)
    var timeCreate: Date = Date(),

    @field:JsonIgnore
    @Column(nullable = false)
    var deleted: String = "N",
)
