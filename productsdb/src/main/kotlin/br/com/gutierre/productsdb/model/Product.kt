package br.com.gutierre.productsdb.model

import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "product_id", nullable = false, length = 10)
    var productId: String = "",

    @Column(name = "descricao",nullable = false, length = 200)
    var description: String = "",

    @Column(name = "categoria", nullable = false, length = 10)
    var category: Long = 0,

    @Column(name = "grupo_id", nullable = false, length = 10)
    var groupId: String = "",

    @Column(name = "subgrupo_id", nullable = false, length = 10)
    var subGroupId: String = "",

    @Column(name = "preco", nullable = false)
    var price: Double = 0.0,

    @Column(name = "codigo_barra", nullable = false, length = 14)
    var ean: String = "",

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(nullable = false, length = 2)
    var un: String = "",

    @Column(nullable = false, length = 2)
    var segUn: String = "",

    @Column(name = "quantidade_limite", nullable = false, length = 50)
    var quantLimProd: String = "",

    @Column(name = "multiplo_venda", nullable = false)
    var multVend: Int = 0,

    @Column(name = "imagem", nullable = false, length = 100)
    var img: String = "",

    @Column(name = "imagem_media", nullable = false, length = 300)
    var imgMed: String = "",

    @Column(name = "imagem_largar", nullable = false, length = 300)
    var imgLarg: String = "",

)
