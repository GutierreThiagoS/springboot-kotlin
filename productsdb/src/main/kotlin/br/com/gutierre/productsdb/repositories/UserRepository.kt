package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User?, Long?> {

    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    fun findByUserName(@Param("userName") userName: String?): User?

    @Modifying
    @Transactional
    @Query("INSERT INTO User (userName, fullName, email, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled) VALUES (:userName, :fullName, :email, :password, false, false, false, false)")
    fun insert(
        @Param("userName") userName: String,
        @Param("fullName") fullName: String,
        @Param("email") email: String,
        @Param("password") password: String,
    ): Int

}