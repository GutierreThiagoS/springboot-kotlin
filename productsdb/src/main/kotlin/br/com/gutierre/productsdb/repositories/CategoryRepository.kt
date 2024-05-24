package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CategoryRepository: JpaRepository<Category, Long?> {

    @Query(value = "SELECT C FROM Category C WHERE C.description = :description AND filial = :filial")
    fun findCategoryName(
        @Param("description") description: String,
        @Param("filial") filial: String
    ): Category?

    @Query(value = "SELECT C FROM Category C WHERE C.id = :categoryId")
    fun findCategoryId(
            @Param("categoryId") categoryId: Long
    ): Category?
}