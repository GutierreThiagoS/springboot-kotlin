package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.ItemOrder
import br.com.gutierre.productsdb.repositories.ItemOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.util.*
import java.util.logging.Logger

@Service
class ItemOrderService {

    @Autowired
    private lateinit var repository: ItemOrderRepository

    private val logger = Logger.getLogger(ItemOrderService::class.java.name)


    fun getAll(): List<ItemOrder> {
        logger.info("Finding all products")

        return repository.findAll()
    }

    fun findById(id: Long): ItemOrder {
        logger.info("Create one product with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(itemOrder: ItemOrder?): ItemOrder {

        logger.info("Create one product with name $itemOrder")

        val itemOrderNotNull = productIsNullOrValuesBlankException(itemOrder)

        return repository.save(itemOrderNotNull)
    }

    fun update(itemOrder: ItemOrder?): ItemOrder {
        logger.info("Update one product with name $itemOrder")

        val itemOrderNotNull = productIsNullOrValuesBlankException(itemOrder)

        val entity: ItemOrder = repository.findById(itemOrderNotNull.id)
            .orElseThrow { ResourceNotFoundException("No Records found for this ID!") }

        entity.productId = itemOrderNotNull.productId
        entity.orderId = itemOrderNotNull.orderId
        entity.quantity = itemOrderNotNull.quantity
        entity.price = itemOrderNotNull.price
        entity.priceTotal = itemOrderNotNull.priceTotal
        entity.filial = itemOrderNotNull.filial
        entity.itemDate = itemOrderNotNull.itemDate
        entity.timeStamp = Date()

        return repository.save(entity)
    }

    private fun productIsNullOrValuesBlankException(itemOrder: ItemOrder?): ItemOrder {

        if (itemOrder == null) throw RequiredObjectIsNullException()

        itemOrder.apply {
            when {
                productId == 0L -> throw RequiredObjectIsNullException("O Campo 'productId' esta Vázia!")
                orderId == 0L -> throw RequiredObjectIsNullException("O Campo 'orderId' esta Vázia!")
                price == 0f -> throw RequiredObjectIsNullException("O Campo 'preço' esta Zerado!")
                priceTotal == 0f -> throw RequiredObjectIsNullException("O Campo 'preço total' esta Zerado!")
                quantity == 0 -> throw RequiredObjectIsNullException("O Campo 'quantidade' esta Zerado!")
                itemDate.isBlank() -> throw RequiredObjectIsNullException("O Campo 'itemDate' esta Vázia!")
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
            }
        }

        return itemOrder
    }
}