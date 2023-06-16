package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Orders
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Orders, Long?> {
}