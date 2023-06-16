package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CategoryRepository: JpaRepository<Category, Long?> {

    @Query(value = "SELECT * FROM Category WHERE descricao = :description AND filial = :filial LIMIT 1", nativeQuery = true)
    fun findCategoryName(
        @Param("description") description: String,
        @Param("filial") filial: String
    ): Category?
}