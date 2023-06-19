package br.com.gutierre.productsdb.repositories.question

import br.com.gutierre.productsdb.model.ItemQuestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ItemQuestionRepository: JpaRepository<ItemQuestion, Long?> {

    @Query(value = "SELECT * FROM item_pergunta WHERE question_id = :questionId LIMIT 4", nativeQuery = true)
    fun getItemsInQuestion(
        @Param("questionId") questionId: Long
    ): List<ItemQuestion>

    @Query(value = "SELECT * FROM item_pergunta WHERE id = :questionItemId AND question_id = :questionId LIMIT 1", nativeQuery = true)
    fun findItemQuestion(
            @Param("questionId") questionId: Long,
            @Param("questionItemId") questionItemId: Long
    ): ItemQuestion?
}