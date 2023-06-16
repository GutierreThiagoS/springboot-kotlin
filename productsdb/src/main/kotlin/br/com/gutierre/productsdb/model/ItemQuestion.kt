package br.com.gutierre.productsdb.model

import jakarta.persistence.*

@Entity
@Table(name = "ItemPergunta")
data class ItemQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "descricao", nullable = false, length = 200)
    var description: String = "",

    @Column(name = "question_id", nullable = false)
    var idQuestion: Long = 0,

    @Column(name = "position_order", nullable = false)
    var positionOrder: Int = 0
)
