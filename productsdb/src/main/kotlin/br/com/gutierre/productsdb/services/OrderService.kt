package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.Orders
import br.com.gutierre.productsdb.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class OrderService {

    @Autowired
    private lateinit var repository: OrderRepository

    private val logger = Logger.getLogger(OrderService::class.java.name)


    fun getAll(): List<Orders> {
        logger.info("Finding all orders")

        return repository.findAll()
    }

    fun findById(id: Long): Orders {
        logger.info("Create one order with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(order: Orders?): Orders {

        logger.info("Create one order with name ${order?.description}")

        val productNotNull = productIsNullOrValuesBlankException(order)

        return repository.save(productNotNull)
    }

    fun update(order: Orders?): Orders {
        logger.info("Update one order with name ${order?.description}")

        val productNotNull = productIsNullOrValuesBlankException(order)

        val entity: Orders = repository.findById(productNotNull.id)
            .orElseThrow { ResourceNotFoundException("No Records found for this ID!") }

        entity.filial = productNotNull.filial
        entity.description = productNotNull.description
        entity.itemDate = productNotNull.itemDate
        entity.priceTotal = productNotNull.priceTotal
        entity.timeStamp = Date()
        entity.finish = productNotNull.finish

        return repository.save(entity)
    }

    private fun productIsNullOrValuesBlankException(order: Orders?): Orders {

        if (order == null) throw RequiredObjectIsNullException()

        order.apply {
            when {
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
                itemDate.isBlank() -> throw RequiredObjectIsNullException("O Campo 'itemDate' esta Vázia!")
            }
        }

        return order
    }
}