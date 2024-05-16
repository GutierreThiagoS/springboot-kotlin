package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long?> {

    @Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT ('%',:firstName,'%'))")
    fun findPersonByName(@Param("firstName") firstName: String, pageable: Pageable) : Page<Person>

    @Modifying
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
    fun disablePerson(@Param("id") id: Long?)
}