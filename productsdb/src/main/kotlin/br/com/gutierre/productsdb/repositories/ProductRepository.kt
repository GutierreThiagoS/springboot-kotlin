package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long?> {
}