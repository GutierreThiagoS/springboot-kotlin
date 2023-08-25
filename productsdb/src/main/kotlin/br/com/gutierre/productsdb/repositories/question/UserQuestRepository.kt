package br.com.gutierre.productsdb.repositories.question

import br.com.gutierre.productsdb.model.UserQuest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserQuestRepository: JpaRepository<UserQuest, Long?> {

    @Query(value = "SELECT * FROM usuario WHERE email = :email LIMIT 1", nativeQuery = true)
    fun findUserEmail(
        @Param("email") email: String
    ): UserQuest?

    @Query(value = "SELECT * FROM usuario WHERE email = :email AND senha = :senha LIMIT 1", nativeQuery = true)
    fun findUserLogin(
        @Param("email") email: String,
        @Param("senha") password: String,
    ): UserQuest?

    @Query(value = "SELECT * FROM usuario WHERE id = :userId LIMIT 1", nativeQuery = true)
    fun findUserId(
            @Param("userId") userId: Long,
    ): UserQuest?
}