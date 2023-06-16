package br.com.gutierre.productsdb.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "usuario")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "nome", nullable = false, length = 200)
    var name: String = "",

    @Column(nullable = false, length = 200)
    var email: String = "",

    @Column(name = "senha", nullable = false, length = 200)
    var password: String = "",

    @Column(name = "cargo", nullable = false, length = 200)
    var office: Int = 0,

    @Column(name = "data_criacao", nullable = false)
    var timeCreate: Date = Date(),

    @Column(nullable = false)
    var deleted: String = "N",
)
