package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.AnswerQuestion
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AnswerQuestionRepository: JpaRepository<AnswerQuestion, Long?> {

    @Query(value = "SELECT * FROM respostas WHERE question_id = :questionId AND question_item_id = :questionItemId LIMIT 1", nativeQuery = true)
    fun findAnswer(
        @Param("questionId") questionId: Long,
        @Param("questionI" +
                "temId") questionItemId: Long
    ): AnswerQuestion?

    @Query(value = "SELECT * FROM pergunta WHERE id = :questionId LIMIT 1", nativeQuery = true)
    fun findQuestion(
        @Param("questionId") questionId: Long,
    ): Question?

    @Query(value = "SELECT * FROM item_pergunta WHERE id = :questionItemId AND question_id = :questionId LIMIT 1", nativeQuery = true)
    fun findItemQuestion(
        @Param("questionId") questionId: Long,
        @Param("questionItemId") questionItemId: Long
    ): ItemQuestion?
}