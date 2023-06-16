package br.com.gutierre.productsdb.model

import jakarta.persistence.*
import java.util.Date

@Entity
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "descricao", nullable = false, length = 200)
    var description: String = "",

    @Column(name = "category_id", nullable = false)
    var categoryId: Long = 0,

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(name = "imagem", nullable = false, length = 100)
    var img: String = "",

    @Column(name = "data_categoria")
    val date: Date? = null
)
