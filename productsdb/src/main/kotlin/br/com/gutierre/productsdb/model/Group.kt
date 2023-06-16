package br.com.gutierre.productsdb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Grupo")
data class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "grupo_id", nullable = false, length = 10)
    var groupId: String = "",

    @Column(name = "descricao",nullable = false, length = 200)
    var description: String = "",

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(name = "imagem", nullable = false, length = 100)
    var img: String = "",
)