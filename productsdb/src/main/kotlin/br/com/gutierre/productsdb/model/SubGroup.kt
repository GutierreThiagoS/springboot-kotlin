package br.com.gutierre.productsdb.model

import jakarta.persistence.*

@Entity
data class SubGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "grupo_id", nullable = false, length = 10)
    var groupId: String = "",

    @Column(name = "subgrupo_id", nullable = false, length = 10)
    var subGroupId: String = "",

    @Column(name = "descricao",nullable = false, length = 200)
    var description: String = "",

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(name = "imagem", nullable = false, length = 100)
    var img: String = "",
)