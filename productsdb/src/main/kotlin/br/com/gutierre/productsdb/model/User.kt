package br.com.gutierre.productsdb.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "nome", nullable = false, length = 200)
    var name: String,

    @Column(nullable = false, length = 200)
    var email: String,

    @Column(name = "senha", nullable = false, length = 200)
    var password: String,

    @Column(name = "cargo", nullable = false, length = 200)
    var office: Int,

    @Column(name = "data_criacao", nullable = false)
    var timeCreate: Date = Date(),
)
