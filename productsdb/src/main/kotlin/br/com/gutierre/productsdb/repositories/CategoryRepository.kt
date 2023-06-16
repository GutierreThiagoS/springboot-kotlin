package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}