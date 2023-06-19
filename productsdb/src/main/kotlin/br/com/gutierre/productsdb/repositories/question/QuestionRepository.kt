package br.com.gutierre.productsdb.repositories.question

import br.com.gutierre.productsdb.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface QuestionRepository: JpaRepository<Question, Long?> {

    @Query(value = """
        SELECT P.* FROM pergunta P
        WHERE ( P.usuario_dest = :userId OR P.usuario_dest = 0 )
        AND (
            SELECT COUNT(*) FROM pergunta PR
            INNER JOIN respostas R ON PR.id = R.question_id 
            WHERE R.usuario = :userId 
            AND PR.id = P.id
        ) = 0
    """, nativeQuery = true)
    fun getAllQuestionUser(
            @Param("userId") userId: Long,
    ): List<Question>

    @Query(value = "SELECT * FROM pergunta WHERE id = :questionId AND (usuario_dest = :userId OR usuario_dest = 0) LIMIT 1", nativeQuery = true)
    fun findQuestion(
            @Param("questionId") questionId: Long,
            @Param("userId") userId: Long,
    ): Question?
}