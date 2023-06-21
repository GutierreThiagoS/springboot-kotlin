package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.Product
import br.com.gutierre.productsdb.repositories.CategoryRepository
import br.com.gutierre.productsdb.repositories.GroupRepository
import br.com.gutierre.productsdb.repositories.ProductRepository
import br.com.gutierre.productsdb.repositories.SubGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ProductsService {

    @Autowired
    private lateinit var repository: ProductRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var groupRepository: GroupRepository

    @Autowired
    private lateinit var subGroupRepository: SubGroupRepository

    private val logger = Logger.getLogger(ProductsService::class.java.name)


    fun getAll(): List<Product> {
        logger.info("Finding all products")

        return repository.findAll()
    }

    fun findById(id: Long): Product {
        logger.info("Create one product with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(product: Product?): Product {

        logger.info("Create one product with name ${product?.description}")

        val productNotNull = productIsNullOrValuesBlankException(product)

        val category = categoryRepository.findCategoryId(productNotNull.category)
        val group = groupRepository.findGroupIdInFilial(productNotNull.groupId, productNotNull.filial)
        val subGroup = subGroupRepository.findSubGroupIdInFilial(productNotNull.subGroupId, productNotNull.filial)

        when {
            group == null && subGroup == null && category == null ->  throw RequiredObjectIsNullException("O 'Grupo, SubGrupo e Categoria' não constam na base de dados!")
        }

        return repository.save(productNotNull)
    }

    fun update(product: Product?): Product {
        logger.info("Update one product with name ${product?.description}")

        val productNotNull = productIsNullOrValuesBlankException(product)

        val entity: Product = repository.findById(productNotNull.id)
            .orElseThrow { ResourceNotFoundException("No Records found for this ID!") }

        entity.description = productNotNull.description
        entity.groupId = productNotNull.groupId
        entity.subGroupId = productNotNull.subGroupId
        entity.price = productNotNull.price
        entity.ean = productNotNull.ean
        entity.filial = productNotNull.filial
        entity.un = productNotNull.un
        entity.segUn = productNotNull.segUn
        entity.quantLimProd = productNotNull.quantLimProd
        entity.img = productNotNull.img
        entity.imgMed = productNotNull.imgMed
        entity.imgLarg = productNotNull.imgLarg

        return repository.save(entity)
    }

    private fun productIsNullOrValuesBlankException(product: Product?): Product {

        if (product == null) throw RequiredObjectIsNullException()

        product.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'description' esta Vázia!")
                groupId.isBlank() && category == 0L -> throw RequiredObjectIsNullException("O Campo 'grupo ou categoria' esta Vázia!")
                subGroupId.isBlank() && category == 0L -> throw RequiredObjectIsNullException("O Campo 'subgrupo' esta Vázia!")
                ean.isBlank() -> throw RequiredObjectIsNullException("O Campo 'ean' esta Vázia!")
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
                un.isBlank() -> throw RequiredObjectIsNullException("O Campo 'un' esta Vázia!")
                segUn.isBlank() -> throw RequiredObjectIsNullException("O Campo 'segUn' esta Vázia!")
            }
        }

        return product
    }

}