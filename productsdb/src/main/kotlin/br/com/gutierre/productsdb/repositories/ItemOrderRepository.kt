package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.ItemOrder
import org.springframework.data.jpa.repository.JpaRepository

interface ItemOrderRepository: JpaRepository<ItemOrder, Long?> {
}