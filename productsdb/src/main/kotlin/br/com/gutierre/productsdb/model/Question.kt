package br.com.gutierre.productsdb.model

import jakarta.persistence.*

@Entity
@Table(name = "pergunta")
data class Question(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "usuario_dest", nullable = false)
    var userId: Long = 0,

    @Column(name = "titulo", nullable = false, length = 200)
    var title: String = "",

    @Column(name = "descricao", nullable = false, length = 300)
    var description: String = "",

    @Column(name = "deletado", nullable = false)
    var deleted: String = "N"
)
