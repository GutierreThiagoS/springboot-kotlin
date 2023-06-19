package br.com.gutierre.productsdb.repositories.question

import br.com.gutierre.productsdb.model.AnswerQuestion
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AnswerQuestionRepository: JpaRepository<AnswerQuestion, Long?> {

    @Query(value = """
            SELECT * FROM respostas 
            WHERE question_id = :questionId 
            AND question_item_id = :questionItemId 
            AND usuario = :userId 
            AND :userId <> 0 
            LIMIT 1
        """, nativeQuery = true)
    fun findAnswer(
        @Param("questionId") questionId: Long,
        @Param("questionItemId") questionItemId: Long,
        @Param("userId") userId: Long,
    ): AnswerQuestion?
}