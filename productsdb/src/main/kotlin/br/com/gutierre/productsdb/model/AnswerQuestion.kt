package br.com.gutierre.productsdb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "respostas")
data class AnswerQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "question_id", nullable = false, length = 200)
    var questionId: Long = 0,

    @Column(name = "question_item_id", nullable = false, length = 200)
    var questionItemId: Long = 0,

    @Column(name = "descricao", nullable = false, length = 200)
    var description: String = "",

    @Column(name = "usuario", nullable = false)
    var userId: Long = 0,

    @Column(name = "data_resposta")
    var timeResponse: Date = Date()
)